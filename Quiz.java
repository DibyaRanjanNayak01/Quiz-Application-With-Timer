import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz
{
    private static final int TIME_LIMIT = 10;
    private static int currentScore = 0;
    private static int currentQuestion = 0;
    private static boolean answered = false;
    private static Timer timer;
    private static final String[][] questions =
    {
        {"What is the capital of India?", "A) Chandigarh", "B) New Delhi", "C) Kolkata", "D) Bangalore", "B"},
        {"Who wrote 'Jana Gana Mana'?", "A) Mahatma Gandhi", "B) Bankim Chandra Chatterjee", "C) Sarojini Naidu", "D) Rabindranath Tagore", "D"},
        {"Who composed 'Vande Mataram'?", "A) Bankim Chandra Chattopadhyay", "B) Sardar Vallabhbhai Patel", "C) Jawaharlal Nehru", "D) Bankim Chandra Chatterjee", "A"},
        {"When did India get its Independence?", "A) 14th Aug, 1947", "B) 26th Jan, 1950", "C) 15th Aug, 1947", "D) 27th Jan, 1950", "C"},
        {"Who is the father of Indian Constitution?", "A) Mahatma Gandhi", "B) Bal Gangadhar Tilak", "C) Subhash Chandra Bose", "D) Dr. B.R.Ambedkar", "D"}
    };
    private static final boolean[] results = new boolean[questions.length];
    private static final String[] userAnswers = new String[questions.length];
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        while (currentQuestion < questions.length)
        {
            displayQuestion(currentQuestion);
            timer = new Timer();
            answered = false;
            timer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    if (!answered)
                    {
                        System.out.println("\nTime's up!");
                        results[currentQuestion] = false;
                        userAnswers[currentQuestion] = "No answer";
                        currentQuestion++;
                        if (currentQuestion < questions.length)
                        {
                            main(null);
                        }
                        else
                        {
                            displayResults();
                        }
                        timer.cancel();
                    }
                }
            }, TIME_LIMIT * 1000);
            String answer = scanner.nextLine().trim().toUpperCase();
            answered = true;
            timer.cancel();
            userAnswers[currentQuestion] = answer;
            if (answer.equals(questions[currentQuestion][5]))
            {
                currentScore++;
                results[currentQuestion] = true;
                System.out.println("Correct!");
            }
            else
            {
                results[currentQuestion] = false;
                System.out.println("Incorrect! The correct answer is: " + questions[currentQuestion][5]);
            }
            currentQuestion++;
        }
        displayResults();
        scanner.close();
    }
    private static void displayQuestion(int questionIndex)
    {
        System.out.println("Question " + (questionIndex + 1) + ": " + questions[questionIndex][0]);
        for (int i = 1; i <= 4; i++)
        {
            System.out.println(questions[questionIndex][i]);
        }
        System.out.println("You have " + TIME_LIMIT + " seconds to answer.");
    }
    private static void displayResults()
    {
        System.out.println("\nQuiz Over! Your final score is: " + currentScore + " out of " + questions.length);
        System.out.println("Summary of correct/incorrect answers:");
        for (int i = 0; i < questions.length; i++)
        {
            System.out.println("Question " + (i + 1) + ": " + (results[i] ? "Correct" : "Incorrect"));
            if (!results[i])
            {
                System.out.println("Correct answer: " + questions[i][5]);
            }
        }
    }
}