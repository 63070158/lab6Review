package com.example.lab6review.view;

import com.example.lab6review.pojo.Wizard;
import com.example.lab6review.pojo.Wizards;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route(value="/mainpage.it")
public class MainWizardView extends VerticalLayout {
    private TextField fullname, dollar;
    private RadioButtonGroup gender;
    private ComboBox position, school, house;
    private Button left, create, update, delete, right;
    private HorizontalLayout h1;



    public Wizards wizards;
    private int index_person;
    public MainWizardView() {
        fullname = new TextField();
        fullname.setLabel("Fullname");
        fullname.setPrefixComponent(new Span("Fullname"));
        gender = new RadioButtonGroup();
        gender.setItems("Male", "Female");
        position = new ComboBox();
        position.setPlaceholder("Position");
        position.setItems("Student", "Teacher");
        dollar = new TextField();
        dollar.setLabel("Dollars");
        dollar.setPrefixComponent(new Span("$"));
        school = new ComboBox();
        school.setPlaceholder("School");
        school.setItems("Hodwarts", "Bequxbatons", "Durmstrang");
        house = new ComboBox();
        house.setItems("Gryffindor", "Ravnclaw", "Hufflepuff", "Slyther");
        left = new Button(" <<");
        create = new Button("Create");
        update = new Button("update");
        delete = new Button("Delete");
        right = new Button(">>");
        h1 = new HorizontalLayout();
        h1.add(left, create, update, delete, right);
        this.add(fullname, gender, position, dollar, school, position, h1);
        dataLoad();

        left.addClickListener(event -> {
            if(index_person - 1 < 0) {
                index_person = 0;
                show_data();
            } else {
                index_person -= 1;
                show_data();
            }
        });
        right.addClickListener(event ->{
            if(index_person + 1 >= this.wizards.model.size()){
                index_person =  this.wizards.model.size() - 1;
                show_data();
            } else {
                index_person += 1;
                show_data();
            }
        });
    }
    private void show_data(){
        if (this.wizards.model.size() != 0){
            this.fullname.setValue(this.wizards.model.get(index_person).getName());
            this.dollar.setValue(this.wizards.model.get(index_person).getMoney());
            this.position.setValue(this.wizards.model.get(index_person).getPosition());
            this.school.setValue(this.wizards.model.get(index_person).getSchool());
            this.house.setValue(this.wizards.model.get(index_person).getHouse());
            this.gender.setValue(this.wizards.model.get(index_person).getSex());

        }
        else{
            this.fullname.setValue("");
            this.dollar.setValue("");
            this.position.setValue("");
            this.school.setValue("");
            this.house.setValue("");
            this.gender.setValue("m");
        }
    }
    private void dataLoad() {
        this.wizards.model = WebClient.create().get().uri("wizards").retrieve().bodyToMono(new ParameterizedTypeReference<ArrayList<Wizard>>() {}).block();
    }
}
