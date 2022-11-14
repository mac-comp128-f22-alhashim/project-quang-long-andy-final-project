
public class UnitTest {
    public static void main(String[] args) {
        Board board = new Board();
        for (int i=0; i< board.getSize(); i++) {
            for (int j=0; j< board.getSize(); j++) {
                board.unmarked(i, j);
            }
        }
        board.markX(0, 0);
        board.markX(1, 1);
        board.markX(2, 2);
        System.out.println(board.checkWin());

    }
}