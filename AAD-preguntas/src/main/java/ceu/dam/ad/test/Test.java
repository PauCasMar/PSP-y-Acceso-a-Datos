package ceu.dam.ad.test;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ceu.dam.ad.model.Answer;
import ceu.dam.ad.model.Question;
import ceu.dam.ad.model.QuestionMC;
import ceu.dam.ad.model.QuestionSC;
import ceu.dam.ad.model.QuestionTF;
import ceu.dam.ad.model.QuestionType;
import ceu.dam.ad.service.QuestionService;

@Component
public class Test {

	@Autowired
    private QuestionService questionService;


    public void test() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==== QUIZ TEST ====");
        System.out.println("Creando preguntas de ejemplo...");
        System.out.println("Pulsa ENTER para continuar...");
        scanner.nextLine();

        // Cargar tipos
        QuestionType tfType = questionService.getAllQuestionTypes().stream()
                .filter(t -> "TF".equals(t.getCode())).findFirst().orElseThrow();
        QuestionType scType = questionService.getAllQuestionTypes().stream()
                .filter(t -> "SC".equals(t.getCode())).findFirst().orElseThrow();
        QuestionType mcType = questionService.getAllQuestionTypes().stream()
                .filter(t -> "MC".equals(t.getCode())).findFirst().orElseThrow();

        // ==== Crear preguntas TF ====
		QuestionTF q1 = new QuestionTF();
        q1.setText("Alan Turing es considerado el padre de la informática moderna.");
        q1.setType(tfType);
        List<Answer> q1Answers = new ArrayList<>();
        q1Answers.add(createAnswer("Verdadero", true));
        q1Answers.add(createAnswer("Falso", false));
        q1.setAnswers(q1Answers);
        questionService.createQuestion(q1);

        System.out.println("Pregunta TF creada. Pulsa ENTER para ver preguntas TF...");
        scanner.nextLine();

        List<Question> tfQuestions = questionService.getAllQuestionByType("TF");
        for (Question q : tfQuestions) {
            printQuestion(q);
            scanner.nextLine();
        }

        // ==== Crear preguntas SC ====
        QuestionSC q2 = new QuestionSC();
        q2.setText("¿Quién desarrolló el lenguaje de programación C?");
        q2.setType(scType);
        List<Answer> q2Answers = new ArrayList<>();
        q2Answers.add(createAnswer("Dennis Ritchie", true));
        q2Answers.add(createAnswer("Bjarne Stroustrup", false));
        q2Answers.add(createAnswer("James Gosling", false));
        q2.setAnswers(q2Answers);
        questionService.createQuestion(q2);

        System.out.println("Pregunta SC creada. Pulsa ENTER para ver preguntas SC...");
        scanner.nextLine();

        List<Question> scQuestions = questionService.getAllQuestionByType("SC");
        for (Question q : scQuestions) {
            printQuestion(q);
            scanner.nextLine();
        }

        // ==== Crear preguntas MC ====
        QuestionMC q3 = new QuestionMC();
        q3.setText("Seleccione todos los lenguajes de programación creados en los años 70.");
        q3.setType(mcType);
        List<Answer> q3Answers = new ArrayList<>();
        q3Answers.add(createAnswer("C", true));
        q3Answers.add(createAnswer("Pascal", true));
        q3Answers.add(createAnswer("Python", false));
        q3Answers.add(createAnswer("Java", false));
        q3.setAnswers(q3Answers);
        questionService.createQuestion(q3);

        System.out.println("Pregunta MC creada. Pulsa ENTER para ver preguntas MC...");
        scanner.nextLine();

        List<Question> mcQuestions = questionService.getAllQuestionByType("MC");
        for (Question q : mcQuestions) {
            printQuestion(q);
            scanner.nextLine();
        }

        
        testGetAllQuestions(scanner);
        System.out.println("Pulsa ENTER para continuar...");
        scanner.nextLine();

        testGetQuestionsByType("TF", scanner);

        testUpdateQuestion(1L, "Texto actualizado de la pregunta TF");
        testDeleteQuestion(2L);
        
        
        System.out.println("==== FIN DE PRUEBAS ====");
        scanner.close();
    }

    private void printQuestion(Question q) {
        System.out.println("Pregunta: " + q.getText());
        System.out.println("Tipo: " + q.getType().getCode());
        for (Answer a : q.getAnswers()) {
            System.out.println(" - " + a.getText() + " [" + (a.getCorrect() ? "Correcta" : "Incorrecta") + "]");
        }
    }

    private Answer createAnswer(String text, boolean correct) {
        Answer a = new Answer();
        a.setText(text);
        a.setCorrect(correct);
        return a;
    }
    
    private void testGetAllQuestions(Scanner scanner) {
        System.out.println("=== Todas las preguntas ===");
        List<Question> allQuestions = questionService.getAllQuestions();
        for (Question q : allQuestions) {
            printQuestion(q);
            scanner.nextLine();
        }
    }

    private void testGetQuestionsByType(String typeCode, Scanner scanner) {
        System.out.println("=== Preguntas de tipo " + typeCode + " ===");
        List<Question> questions = questionService.getAllQuestionByType(typeCode);
        for (Question q : questions) {
            printQuestion(q);
            scanner.nextLine();
        }
    }

    private void testUpdateQuestion(Long questionId, String newText) {
        try {
            Question q = questionService.getQuestionById(questionId);
            q.setText(newText);
            Question updated = questionService.updateQuestion(q);
            System.out.println("Pregunta actualizada: " + updated.getText());
        } catch (Exception e) {
            System.out.println("Error al actualizar pregunta: " + e.getMessage());
        }
    }

    private void testDeleteQuestion(Long questionId) {
        try {
            questionService.deleteQuestion(questionId);
            System.out.println("Pregunta con ID " + questionId + " borrada.");
        } catch (Exception e) {
            System.out.println("Error al borrar pregunta: " + e.getMessage());
        }
    }
}