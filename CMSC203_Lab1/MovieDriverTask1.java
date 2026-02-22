import java.util.Scanner;

public class MovieDriverTask1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keybd = new Scanner(System.in);
		
		Movie mymovie = new Movie();
		
		//prompt user for movie title
		System.out.println("Please enter a movie title: ");
		mymovie.setTitle(keybd.nextLine());
		
		//prompt user for a movie rating
		System.out.println("Please enter the movie's rating: ");
		mymovie.setRating(keybd.nextLine());
		
		//prompt user for the tickets sold
		System.out.println("Please enter the number of tickets sold: ");
		mymovie.setSoldTickets(keybd.nextInt());
		
		// throw away line
		keybd.nextLine();

		System.out.println("Movie information");
		System.out.println(mymovie.toString());
	}

}
