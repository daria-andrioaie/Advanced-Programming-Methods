package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ADTs.MyDictionary;
import model.ADTs.MyHeap;
import model.ADTs.MyList;
import model.ADTs.MyStack;
import model.ProgramState;
import model.expressions.*;
import model.statements.*;
import model.types.*;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;
import repository.MemoryRepository;
import repository.RepositoryInterface;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class ProgramsListView {
    private final List<Controller> controllers = new ArrayList<>();
    private final List<Statement> programs = new ArrayList<>();

    @FXML
    private ListView<String> programsListView = new ListView<>();

    public ProgramsListView(){}


    @FXML
    public void runProgram(MouseEvent mouseEvent) {
        if (programsListView.getSelectionModel().getSelectedItem() != null) {
            try {
                // initialise the window which runs each program
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProgramExecution.fxml"));
                Parent programView = loader.load();

                // set the controller as the one of the selected program
                ProgramExecution controller = loader.getController();
                controller.setController(controllers.get(programsListView.getSelectionModel().getSelectedIndex()));

                Stage stage = new Stage();
                stage.setTitle("Program Execution");
                stage.setScene(new Scene(programView));
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("You need to select one of the programs");
            alert.setContentText("Please select a program!");
            alert.showAndWait();
        }
    }

    public void populateListView(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("TypeCheck Error");

        /// example 1
        Statement example1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        MyStack<Statement> executionStack1 = new MyStack<>();
        MyList<Value> standardOutput1 = new MyList<>();
        MyDictionary<String, Value> symbolTable1 = new MyDictionary<>();
        MyDictionary<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();
        MyHeap<Integer, Value> heap1 = new MyHeap<>();

        try{
            MyDictionary<String, Type> typeEnvironment1 = new MyDictionary<>();
            example1.typeCheck(typeEnvironment1);
            ProgramState program1 = new ProgramState(executionStack1, symbolTable1, standardOutput1, heap1, fileTable1 , example1);
            List<ProgramState> threadList1 = new ArrayList<>();
            threadList1.add(program1);
            RepositoryInterface repo1 = new MemoryRepository(threadList1, "C:\\Users\\daria\\Documents\\study\\facultate\\anul 2\\Semestrul 1\\Metode de programare\\Lab\\ToyInterpreter\\log1.txt");
            Controller controller1 = new Controller(repo1);
            controllers.add(controller1);
            programs.add(example1);
        }
        catch (Exception e){
            alert.setContentText("Example 1 TypeCheck error: " + e.getMessage());
            alert.showAndWait();
        }


        /// example 2
        Statement example2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression("*",new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression("+" ,new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));

        try{
            MyDictionary<String, Type> typeEnvironment2 = new MyDictionary<>();
            example2.typeCheck(typeEnvironment2);
            MyStack<Statement> executionStack2 = new MyStack<>();
            MyList<Value> standardOutput2 = new MyList<>();
            MyDictionary<String, Value> symbolTable2 = new MyDictionary<>();
            MyDictionary<StringValue, BufferedReader> fileTable2 = new MyDictionary<>();
            MyHeap<Integer, Value> heap2 = new MyHeap<>();

            ProgramState program2 = new ProgramState(executionStack2, symbolTable2, standardOutput2, heap2, fileTable2 , example2);
            List<ProgramState> threadList2 = new ArrayList<>();
            threadList2.add(program2);
            RepositoryInterface repo2 = new MemoryRepository(threadList2, "log2.txt");
            Controller controller2 = new Controller(repo2);
            controllers.add(controller2);
            programs.add(example2);


        }
        catch(Exception e){
            alert.setContentText("Example 2 TypeCheck error: " + e.getMessage());
            alert.showAndWait();
        }


        /// example 3
        Statement example3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                        IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
        try{
            MyDictionary<String, Type> typeEnvironment3 = new MyDictionary<>();
            example3.typeCheck(typeEnvironment3);
            MyStack<Statement> executionStack3 = new MyStack<>();
            MyList<Value> standardOutput3 = new MyList<>();
            MyDictionary<String, Value> symbolTable3 = new MyDictionary<>();
            MyDictionary<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();
            MyHeap<Integer, Value> heap3 = new MyHeap<>();

            ProgramState program3 = new ProgramState(executionStack3, symbolTable3, standardOutput3, heap3, fileTable3 , example3);
            List<ProgramState> threadList3 = new ArrayList<>();
            threadList3.add(program3);
            RepositoryInterface repo3 = new MemoryRepository(threadList3, "log3.txt");
            Controller controller3 = new Controller(repo3);
            controllers.add(controller3);
            programs.add(example3);

        }
        catch(Exception e){
            alert.setContentText("Example 3 TypeCheck error: " + e.getMessage());
            alert.showAndWait();        }


        /// example 4
        Statement example4 = new CompoundStatement(new VariableDeclarationStatement("filePath", new StringType()),
                new CompoundStatement(new AssignmentStatement("filePath", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenReadFileStatement(new VariableExpression("filePath")),
                                new CompoundStatement(new VariableDeclarationStatement("variable", new IntType()),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("filePath"), "variable"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("variable")),
                                                        new CloseReadFileStatement(new VariableExpression("filePath"))))))));

        try{
            MyDictionary<String, Type> typeEnvironment4 = new MyDictionary<>();
            example4.typeCheck(typeEnvironment4);
            MyStack<Statement> executionStack4 = new MyStack<>();
            MyList<Value> standardOutput4 = new MyList<>();
            MyDictionary<String, Value> symbolTable4 = new MyDictionary<>();
            MyDictionary<StringValue, BufferedReader> fileTable4 = new MyDictionary<>();
            MyHeap<Integer, Value> heap4 = new MyHeap<>();

            ProgramState program4 = new ProgramState(executionStack4, symbolTable4, standardOutput4, heap4, fileTable4 , example4);
            List<ProgramState> threadList4 = new ArrayList<>();
            threadList4.add(program4);
            RepositoryInterface repo4 = new MemoryRepository(threadList4, "log4.txt");
            Controller controller4 = new Controller(repo4);
            controllers.add(controller4);
            programs.add(example4);

        }
        catch(Exception e){
            alert.setContentText("Example 4 TypeCheck error: " + e.getMessage());
            alert.showAndWait();
        }

        /// example 5
        //  int v; v=4; (while (v>0) print(v);v=v-1);print(v)

        Statement example5 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v",
                                        new ArithmeticExpression("-", new VariableExpression("v"), new ValueExpression(new IntValue(1)))))), new PrintStatement(new VariableExpression("v")))));

        try{
            MyDictionary<String, Type> typeEnvironment5 = new MyDictionary<>();
            example5.typeCheck(typeEnvironment5);
            MyStack<Statement> executionStack5 = new MyStack<>();
            MyList<Value> standardOutput5 = new MyList<>();
            MyDictionary<String, Value> symbolTable5 = new MyDictionary<>();
            MyDictionary<StringValue, BufferedReader> fileTable5 = new MyDictionary<>();
            MyHeap<Integer, Value> heap5 = new MyHeap<>();

            ProgramState program5 = new ProgramState(executionStack5, symbolTable5, standardOutput5, heap5, fileTable5 , example5);
            List<ProgramState> threadList5 = new ArrayList<>();
            threadList5.add(program5);
            RepositoryInterface repo5 = new MemoryRepository(threadList5, "log5.txt");
            Controller controller5 = new Controller(repo5);
            controllers.add(controller5);
            programs.add(example5);
        }
        catch(Exception e){
            alert.setContentText("Example 5 TypeCheck error: " + e.getMessage());
            alert.showAndWait();
        }


        // example 6
        // Ref int v; new(v, 23); Ref Ref int a; new(a, v); print(v); print(a);
        Statement example6 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(23))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a")))))));
        try{
            MyDictionary<String, Type> typeEnvironment6 = new MyDictionary<>();
            example6.typeCheck(typeEnvironment6);
            MyStack<Statement> executionStack6 = new MyStack<>();
            MyList<Value> standardOutput6 = new MyList<>();
            MyDictionary<String, Value> symbolTable6 = new MyDictionary<>();
            MyDictionary<StringValue, BufferedReader> fileTable6 = new MyDictionary<>();
            MyHeap<Integer, Value> heap6 = new MyHeap<>();

            ProgramState program6 = new ProgramState(executionStack6, symbolTable6, standardOutput6, heap6, fileTable6 , example6);
            List<ProgramState> threadList6 = new ArrayList<>();
            threadList6.add(program6);
            RepositoryInterface repo6 = new MemoryRepository(threadList6, "log6.txt");
            Controller controller6 = new Controller(repo6);
            controllers.add(controller6);
            programs.add(example6);
        }
        catch(Exception e){
            alert.setContentText("Example 6 TypeCheck error: " + e.getMessage());
            alert.showAndWait();
        }

        /// example 7
        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)

        Statement example7 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression("+", new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))),
                                                        new ValueExpression(new IntValue(5)))))))));
        try{
            MyDictionary<String, Type> typeEnvironment7 = new MyDictionary<>();
            example7.typeCheck(typeEnvironment7);
            MyStack<Statement> executionStack7 = new MyStack<>();
            MyList<Value> standardOutput7 = new MyList<>();
            MyDictionary<String, Value> symbolTable7 = new MyDictionary<>();
            MyDictionary<StringValue, BufferedReader> fileTable7= new MyDictionary<>();
            MyHeap<Integer, Value> heap7 = new MyHeap<>();

            ProgramState program7 = new ProgramState(executionStack7, symbolTable7, standardOutput7, heap7, fileTable7 , example7);
            List<ProgramState> threadList7 = new ArrayList<>();
            threadList7.add(program7);
            RepositoryInterface repo7 = new MemoryRepository(threadList7, "log7.txt");
            Controller controller7 = new Controller(repo7);
            controllers.add(controller7);
            programs.add(example7);
        }
        catch(Exception e) {
            alert.setContentText("Example 7 TypeCheck error: " + e.getMessage());
            alert.showAndWait();
        }


        /// example 8
        // Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);

        Statement example8 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                new CompoundStatement(new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression("+", new HeapReadingExpression(new VariableExpression("v")),new ValueExpression(new IntValue(5))))))));
        try{
            MyDictionary<String, Type> typeEnvironment8 = new MyDictionary<>();
            example8.typeCheck(typeEnvironment8);
            MyStack<Statement> executionStack8 = new MyStack<>();
            MyList<Value> standardOutput8 = new MyList<>();
            MyDictionary<String, Value> symbolTable8 = new MyDictionary<>();
            MyDictionary<StringValue, BufferedReader> fileTable8 = new MyDictionary<>();
            MyHeap<Integer, Value> heap8 = new MyHeap<>();

            ProgramState program8 = new ProgramState(executionStack8, symbolTable8, standardOutput8, heap8, fileTable8 , example8);
            List<ProgramState> threadList8 = new ArrayList<>();
            threadList8.add(program8);
            RepositoryInterface repo8 = new MemoryRepository(threadList8, "log8.txt");
            Controller controller8 = new Controller(repo8);
            controllers.add(controller8);
            programs.add(example8);
        }
        catch(Exception e){
            alert.setContentText("Example 8 TypeCheck error: " + e.getMessage());
            alert.showAndWait();
        }

        /// Example 9, GARBAGE COLLECTOR
        // Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))

        Statement example9 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement( new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));
        try{
            MyDictionary<String, Type> typeEnvironment9 = new MyDictionary<>();
            example9.typeCheck(typeEnvironment9);
            MyStack<Statement> executionStack9 = new MyStack<>();
            MyList<Value> standardOutput9 = new MyList<>();
            MyDictionary<String, Value> symbolTable9 = new MyDictionary<>();
            MyDictionary<StringValue, BufferedReader> fileTable9 = new MyDictionary<>();
            MyHeap<Integer, Value> heap9 = new MyHeap<>();

            ProgramState program9 = new ProgramState(executionStack9, symbolTable9, standardOutput9, heap9, fileTable9 , example9);
            List<ProgramState> threadList9 = new ArrayList<>();
            threadList9.add(program9);
            RepositoryInterface repo9 = new MemoryRepository(threadList9, "log9.txt");
            Controller controller9 = new Controller(repo9);
            controllers.add(controller9);
            programs.add(example9);
        }
        catch(Exception e){
            alert.setContentText("Example 9 TypeCheck error: " + e.getMessage());
            alert.showAndWait();
        }


        /// concurrent program
        /// int v; Ref int a; v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(rH(a)));
        // print(v);print(rH(a))

        Statement example10 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWritingStatement("a", new ValueExpression(new IntValue(30))),
                                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))))))));
        try{
            MyDictionary<String, Type> typeEnvironment10 = new MyDictionary<>();
            example10.typeCheck(typeEnvironment10);
            MyStack<Statement> executionStack10 = new MyStack<>();
            MyList<Value> standardOutput10 = new MyList<>();
            MyDictionary<String, Value> symbolTable10 = new MyDictionary<>();
            MyDictionary<StringValue, BufferedReader> fileTable10 = new MyDictionary<>();
            MyHeap<Integer, Value> heap10 = new MyHeap<>();

            ProgramState program10 = new ProgramState(executionStack10, symbolTable10, standardOutput10, heap10, fileTable10 , example10);
            List<ProgramState> threadList10 = new ArrayList<>();
            threadList10.add(program10);
            RepositoryInterface repo10 = new MemoryRepository(threadList10, "log10.txt");
            Controller controller10 = new Controller(repo10);
            controllers.add(controller10);
            programs.add(example10);

        }
        catch(Exception e){
            alert.setContentText("Example 10 TypeCheck error: " + e.getMessage());
            alert.showAndWait();
        }

        /// example 11
        // throws an error, in order to emphasize the type checker
        // (bool a;(int b;(a=2 + 3 * 5;(b=a + 1;print (b)))))
        Statement example11 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression("*",new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression("+" ,new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));

        try{
            MyDictionary<String, Type> typeEnvironment11 = new MyDictionary<>();
            example11.typeCheck(typeEnvironment11);
            MyStack<Statement> executionStack11 = new MyStack<>();
            MyList<Value> standardOutput11 = new MyList<>();
            MyDictionary<String, Value> symbolTable11 = new MyDictionary<>();
            MyDictionary<StringValue, BufferedReader> fileTable11 = new MyDictionary<>();
            MyHeap<Integer, Value> heap11 = new MyHeap<>();

            ProgramState program11 = new ProgramState(executionStack11, symbolTable11, standardOutput11, heap11, fileTable11 , example11);
            List<ProgramState> threadList11 = new ArrayList<>();
            threadList11.add(program11);
            RepositoryInterface repo11 = new MemoryRepository(threadList11, "log11.txt");
            Controller controller11 = new Controller(repo11);
            controllers.add(controller11);
            programs.add(example11);
        }
        catch(Exception e){
            alert.setContentText("Example 11 TypeCheck error: " + e.getMessage());
            alert.showAndWait();
        }

/*
        /// concurrent program with 2 fork statements
        /// int v; Ref int a; v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(rH(a)));
        // print(v);print(rH(a))

        Statement example12 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWritingStatement("a", new ValueExpression(new IntValue(30))),
                                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))))))));
        try{
            MyDictionary<String, Type> typeEnvironment12 = new MyDictionary<>();
            example12.typeCheck(typeEnvironment12);
            MyStack<Statement> executionStack12 = new MyStack<>();
            MyList<Value> standardOutput12 = new MyList<>();
            MyDictionary<String, Value> symbolTable12 = new MyDictionary<>();
            MyDictionary<StringValue, BufferedReader> fileTable12 = new MyDictionary<>();
            MyHeap<Integer, Value> heap12 = new MyHeap<>();

            ProgramState program12 = new ProgramState(executionStack12, symbolTable12, standardOutput12, heap12, fileTable12 , example12);
            List<ProgramState> threadList12 = new ArrayList<>();
            threadList12.add(program12);
            RepositoryInterface repo10 = new MemoryRepository(threadList12, "log12.txt");
            Controller controller12 = new Controller(repo10);
            controllers.add(controller12);
            programs.add(example12);
        }
        catch(Exception e){
            alert.setContentText("Example 12 TypeCheck error: " + e.getMessage());
            alert.showAndWait();
        }
*/
        List<String> programsAsStrings = new ArrayList<>();
        int ct = 1;
        for (Statement item : programs) {
            String s = "Example " + ct + ": " + item.toString();
            programsAsStrings.add(s);
            ct += 1;
        }

        ObservableList<String> programsObservableList = FXCollections.observableArrayList(programsAsStrings);
        programsListView.setItems(programsObservableList);
    }
}
