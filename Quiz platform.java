import java.util.*;

// Enum for Question Types
enum QuestionType {
    MULTIPLE_CHOICE,
    TRUE_FALSE,
    SHORT_ANSWER
}

// User Class
class User {
    private Long id;
    private String username;
    private String password;
    private List<Answer> answers = new ArrayList<>();

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getPassword() {
        return password;
    }
}

// Quiz Class
class Quiz {
    private Long id;
    private String title;
    private List<Question> questions = new ArrayList<>();

    public Quiz(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}

// Question Class
class Question {
    private Long id;
    private String questionText;
    private QuestionType questionType;
    private List<String> options;
    private String correctAnswer;

    public Question(Long id, String questionText, QuestionType questionType, List<String> options, String correctAnswer) {
        this.id = id;
        this.questionText = questionText;
        this.questionType = questionType;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public Long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

// Answer Class
class Answer {
    private Long id;
    private Question question;
    private User user;
    private String userAnswer;

    public Answer(Long id, Question question, User user, String userAnswer) {
        this.id = id;
        this.question = question;
        this.user = user;
        this.userAnswer = userAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public User getUser() {
        return user;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public boolean isCorrect() {
        return this.userAnswer.equals(question.getCorrectAnswer());
    }
}

// Main Class to Run the Quiz Platform
public class QuizPlatform {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a user
        User user = new User(1L, "JohnDoe", "password123");

        // Create a quiz
        Quiz quiz = new Quiz(1L, "Java Basics Quiz");

        // Add some questions to the quiz
        List<String> mcOptions = Arrays.asList("Java", "C++", "Python", "JavaScript");
        Question q1 = new Question(1L, "Which programming language is platform-independent?", QuestionType.MULTIPLE_CHOICE, mcOptions, "Java");
        Question q2 = new Question(2L, "Is Java statically typed?", QuestionType.TRUE_FALSE, null, "True");
        Question q3 = new Question(3L, "What is the keyword used to define inheritance in Java?", QuestionType.SHORT_ANSWER, null, "extends");

        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);

        // Simulate taking the quiz
        System.out.println("Welcome to the " + quiz.getTitle());
        List<Answer> userAnswers = new ArrayList<>();

        for (Question question : quiz.getQuestions()) {
            System.out.println("Question: " + question.getQuestionText());

            if (question.getQuestionType() == QuestionType.MULTIPLE_CHOICE) {
                List<String> options = question.getOptions();
                for (int i = 0; i < options.size(); i++) {
                    System.out.println((i + 1) + ". " + options.get(i));
                }
                System.out.print("Choose an option (1-" + options.size() + "): ");
                int option = scanner.nextInt();
                String answer = options.get(option - 1);
                userAnswers.add(new Answer(null, question, user, answer));
            } else if (question.getQuestionType() == QuestionType.TRUE_FALSE) {
                System.out.print("Enter True or False: ");
                String answer = scanner.next();
                userAnswers.add(new Answer(null, question, user, answer));
            } else if (question.getQuestionType() == QuestionType.SHORT_ANSWER) {
                System.out.print("Enter your answer: ");
                scanner.nextLine(); // Consume newline
                String answer = scanner.nextLine();
                userAnswers.add(new Answer(null, question, user, answer));
            }
        }

        // Evaluate the quiz
        int correctAnswers = 0;
        for (Answer answer : userAnswers) {
            if (answer.isCorrect()) {
                correctAnswers++;
            }
        }

        System.out.println("You scored " + correctAnswers + " out of " + quiz.getQuestions().size());

        // Set answers for the user
        user.setAnswers(userAnswers);

        // Quiz Completed
        System.out.println("Quiz Completed! Thank you for participating.");
    }
}
