class Sudoku{
    private int[][] table;
    public Sudoku(int[][] table){
        this.table=table;
    }
    public boolean validTable(){
        int[] frq=new int[10];
        for(int i=1;i<=9;i++)
            frq[i]=0;
        for(int row=0;row<9;row++){
            for(int col=0;col<9;col++)
                if(table[row][col]!=0){
                    frq[table[row][col]]++;
                    if(frq[table[row][col]]>1) return false;
                }
            for(int col=0;col<9;col++)
                if(table[row][col]!=0)
                    frq[table[row][col]]=0;
        }
        for(int col=0;col<9;col++){
            for(int row=0;row<9;row++)
                if(table[row][col]!=0){
                    frq[table[row][col]]++;
                    if(frq[table[row][col]]>1) return false;
                }
            for(int row=0;row<9;row++)
                if(table[row][col]!=0)
                    frq[table[row][col]]=0;
        }
        return true;
    }
    public boolean validNumber(int row, int col, int num){
        for(int i=0;i<9;i++)
            if(table[row][i]==num || table[i][col]==num)
                return false;
        int squareRow=3*(row/3);
        int squareCol=3*(col/3);
        for(int i=squareRow;i<squareRow+3;i++)
            for(int j=squareCol;j<squareCol+3;j++)
                if(table[i][j]==num)
                    return false;
        return true;
    }
    public int[] findEmptySlot(){
        int[] empty={-1,-1};
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
                if(table[i][j]==0){
                    empty[0]=i;
                    empty[1]=j;
                    return empty;
                }
        return empty;
    }
    public boolean fillEmptySlot(){
        int[] emptySlot=findEmptySlot();
        int row=emptySlot[0];
        int col=emptySlot[1];
        if(row==-1 && col==-1)
            return true;
        for(int i=1;i<=9;i++)
            if(validNumber(row,col,i)){
                table[row][col]=i;
                if(fillEmptySlot())
                    return true;
                table[row][col]=0;
            }
        return false;
    }
    public void display(){        
        for(int i=0;i<9;i++){
            if(i%3==0){ 
                for(int l=0;l<25;l++)
                    System.out.print('=');
            System.out.println();
            }
            for(int j=0;j<9;j++){
                if(j%3==0) System.out.print("| ");
                System.out.print(table[i][j] + " ");
            }
            System.out.println('|');
        }
        for(int l=0;l<25;l++)
            System.out.print('=');
    }
}
class sudokuGame{
    public static void main(String[] args){
        int[][] table={
                {0,3,5,2,9,0,8,6,4},
                {0,8,2,4,1,0,7,0,3},
                {7,6,4,3,8,0,0,9,0},
                {2,1,8,7,3,9,0,4,0},
                {0,0,0,8,0,4,2,3,0},
                {0,4,3,0,5,2,9,7,0},
                {4,0,6,5,7,1,0,0,9},
                {3,5,9,0,2,8,4,1,7},
                {8,0,0,9,0,0,5,2,6}};
        // int[][] table={
        //         {0,0,0,0,0,0,0,0,0},
        //         {0,0,0,0,0,0,0,0,0},
        //         {0,0,0,0,0,0,0,0,0},
        //         {0,0,0,0,0,0,0,0,0},
        //         {0,0,0,0,0,0,0,0,0},
        //         {0,0,0,0,0,0,0,0,0},
        //         {0,0,0,0,0,0,0,0,0},
        //         {0,0,0,0,0,0,0,0,0},
        //         {0,0,0,0,0,0,0,0,0}};
        Sudoku sudokuGame=new Sudoku(table);
        if(sudokuGame.validTable()){
            if(sudokuGame.fillEmptySlot())
                sudokuGame.display();
            else System.out.println("No solution exists");
        }
        else System.out.println("Invalid table");
    }
}