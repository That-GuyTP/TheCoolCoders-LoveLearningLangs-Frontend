package com.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Exercise {

    private double progress;
    private ArrayList<Question> questions;
    private int correctAnswers;
    private Language language;
    private double accuracy;
    private Random rand;
    private static Scanner kb = new Scanner(System.in);

    /**
     * Default Constructor
     */
    public Exercise() {
        this.questions = new ArrayList<>();
        this.rand = new Random();
        this.correctAnswers = 0;
        this.accuracy = 0.0;
    }
    
    /**
     * Parameter Constructor 
     * @param language
     * @param progress
     * @param question
     */
    public Exercise(Language language, Double progress) {
        this.language = language;
        this.progress = progress;
        this.questions = new ArrayList<>();
        this.rand = new Random();
        this.correctAnswers = 0;
        this.accuracy = 0.0;
    }

    /**
     * generateQuestions method that creates an arraylist of 10 questions and returns that list.
     * It also generates questions based off the user's langauge chosen, language progress, and a random question type.
     * @return ArrayList(question)
     */
    public ArrayList<Question> generateQuestions() {
        for (int i = 0; i < 10; i++) {
            int randomType = randomQuestionType();
            switch (randomType) {
                case 1:
                    //Multiple Choice
                    questions.add(new MultipleChoice(language, 1.0));
                    break;
                case 2:
                    questions.add(new FillInTheBlank(language, 1.0));
                    break;
                case 3:
                    questions.add(new trueOrFalse(language, 1.0));
                    break;
                default:
                    System.out.println("Error! Questions could not be generated!");
                    break;
            }

        }
        return questions;
    }

    /**
     * Runs the exercise and returns the accuracy score of the user.
     * 
     * @return double
     */
    public double startExercise() {
        
        generateQuestions();
        System.out.println("");
        System.out.println("Beginning Exercise!");
        System.out.println("(Enter the number if one is provided. Otherwise enter a phrase)");
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestion());
            
            if (question instanceof trueOrFalse) {
                System.out.println("1. True");
                System.out.println("2. False");
                trueOrFalse tof = (trueOrFalse) question;
                int userAnswer = Integer.parseInt(kb.nextLine());
                boolean isCorrect = tof.checkAnswer(userAnswer);
                if (isCorrect) {
                    correctAnswers++;
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect! The correct answer was: " + tof.getCorrectAnswer());
                }
            } else if (question instanceof MultipleChoice) {
                MultipleChoice mc = (MultipleChoice) question;
                ArrayList<String> choices = mc.getChoices();
                for (int j = 0; j < choices.size(); j++) {
                    System.out.println((j + 1) + ". " + choices.get(j));
                }
                int userAnswer = Integer.parseInt(kb.nextLine());
                boolean isCorrect = mc.checkAnswer(userAnswer);
                if (isCorrect) {
                    correctAnswers++;
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect! The correct answer was: " + mc.getAnswer());
                }
            } else if (question instanceof FillInTheBlank) {
                String userAnswer = kb.nextLine();
                boolean isCorrect = userAnswer.equalsIgnoreCase(question.getAnswer());
                if (isCorrect) {
                    correctAnswers++;
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect! The correct answer was: " + question.getAnswer());
                }
            }
            System.out.println(""); //Add some room for the next question.
        }
        return calcAccuracy();
    }

    /**
     * getQuestion method that returns the current question index's question string.
     * 
     * @param questionIndex
     * @return Question String
     */
    public String getQuestion(int questionIndex) {
        if (!questions.isEmpty()) {
            return questions.get(questionIndex).getQuestion();
        } else {
            return "Error! Failed to retrieve question";
        }
    }

    /**
     * getAnswer method that returns the answer for the question at the index inputed.
     * 
     * @param questionIndex
     * @return answer for inputed question index
     */
    public String getAnswer(int questionIndex) {
        if (!questions.isEmpty()) {
            return questions.get(questionIndex).getAnswer();
        } else {
            return "ERROR! Failed to retrieve answer";
        }
    }
    
    /**
     * returns the progress of the questions.
     * 
     * @return double Progress
     */
    public Double getProgress() {
        return this.progress;
    }

    /**
     * checkAnswer method that returns whether the user got the correct answer or not.
     * If they did get it correct, it increases the correctAnswers value by 1;
     * 
     * @param questionIndex
     * @param answer
     * @return boolean
     */
    public boolean checkAnswer(int questionIndex, String answer) {
        if (answer == null || answer.isEmpty()) {
            return false;
        } else {
            boolean correct = answer.equalsIgnoreCase(this.getAnswer(questionIndex));
            if (correct) {
                correctAnswers++;
            }
            return correct;
        }
    }

    /**
     * calcAccuracy method that returns the percentage accurary of the user based off 10 questions.
     * 
     * @return accurary
     */
    public double calcAccuracy() {
        double accuracy = (correctAnswers / 10.0) * 100;
        return accuracy;
    }

    /**
     * incProgress method that returns boolean based off the user's accuracy percentage.
     *  
     * @param accuracy
     * @return boolean
     */
    public boolean incProgress(double accuracy) {
        if (accuracy >= 60.0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a random integer between 1-4.
     * 
     * @return Random
     */
    public int randomQuestionType() {
        int n = rand.nextInt(3) + 1; // So it's not 0-2, it's 1-3.
        return n;
        /*
        if (n == 1) {
            Question question = new MultipleChoice(this.language, this.progress);
        } else if (n == 2) {
            Question question = new FillInTheBlank(this.language, this.progress);
        } else if (n == 3) {
            Question question = new trueOrFalse(this.language, this.progress);
        } else {
            Question question = null;
        }
        return question;
         */
    }

}
