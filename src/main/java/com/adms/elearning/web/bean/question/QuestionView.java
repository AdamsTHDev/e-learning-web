package com.adms.elearning.web.bean.question;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;

import com.adms.elearning.web.bean.base.BaseBean;
import com.adms.elearning.web.model.QuestionModel;
import com.adms.elearning.web.model.SectionModel;
import com.adms.elearning.web.util.MessageUtils;

@ManagedBean
@ViewScoped
public class QuestionView extends BaseBean {

	private static final long serialVersionUID = 6633083012737219880L;

	private List<SectionModel> sections;
	private int currSectionNum;
	
	private List<QuestionModel> questions;
	private int currQuestionNum;
	
	private boolean sectionIntro;
	
	public QuestionView() {
		
	}
	
	@PostConstruct
	public void init() {
		sectionIntro = true;
		
		currSectionNum = 0;
		sections = new ArrayList<>();
		
		List<QuestionModel> questions;
		
		SectionModel section = null;
		QuestionModel qm = null;
		List<SelectItem> choices = null;

		currQuestionNum = 0;
		
//		<!-- Section -->
		section = new SectionModel();
		section.setSectionName("Company");
		section.setSectionDesc("Question about Company");
		questions = new ArrayList<>();

//		<!-- Question -->
		qm = new QuestionModel();
		qm.setQuestionText("What is full name of AEGON Thailand?");
		choices = new ArrayList<>();
		choices.add(new SelectItem("A", "Aegon Direct Marketing Ltd."));
		choices.add(new SelectItem("B", "Aegon Direct & Affinity Services Ltd."));
		choices.add(new SelectItem("C", "Aegon Direct & Affinity Marketing Services (Thailand) Ltd."));
		choices.add(new SelectItem("D", "None of above"));
		qm.setChoices(choices);
		questions.add(qm);
		
//		<!-- Question -->
		qm = new QuestionModel();
		qm.setQuestionText("What is Aegon Core Value?");
		choices = new ArrayList<>();
		choices.add(new SelectItem("A", "Relaxing"));
		choices.add(new SelectItem("B", "Work hard"));
		choices.add(new SelectItem("C", "Keep going"));
		choices.add(new SelectItem("D", "Working together"));
		qm.setChoices(choices);
		questions.add(qm);
		
		section.setQuestions(questions);
		sections.add(section);
		
//		<!-- Section -->
		section = new SectionModel();
		section.setSectionName("Life");
		section.setSectionDesc("Question about your life");
		questions = new ArrayList<>();

//		<!-- Question -->
		qm = new QuestionModel();
		qm.setQuestionText("Are you hungry? ______________.");
		choices = new ArrayList<>();
		choices.add(new SelectItem("A", "Just a bit"));
		choices.add(new SelectItem("B", "Not many"));
		choices.add(new SelectItem("C", "Not any"));
		choices.add(new SelectItem("D", "A few"));
		qm.setChoices(choices);
		questions.add(qm);
		
//		<!-- Question -->
		qm = new QuestionModel();
		qm.setQuestionText("How many years of your working experience?");
		choices = new ArrayList<>();
		choices.add(new SelectItem("A", "less than 1"));
		choices.add(new SelectItem("B", "1 - 2 years"));
		choices.add(new SelectItem("C", "3 - 4 years"));
		choices.add(new SelectItem("D", "more than 4 years"));
		qm.setChoices(choices);
		questions.add(qm);
		
//		<!-- Question -->
		qm = new QuestionModel();
		qm.setQuestionText("What is the most important");
		choices = new ArrayList<>();
		choices.add(new SelectItem("A", "Sleeping"));
		choices.add(new SelectItem("B", "Eating"));
		choices.add(new SelectItem("C", "Playing"));
		choices.add(new SelectItem("D", "Nothing"));
		qm.setChoices(choices);
		questions.add(qm);
		
		section.setQuestions(questions);
		sections.add(section);
		
//		<!-- Section -->
		section = new SectionModel();
		section.setSectionName("Math");
		section.setSectionDesc("Question about Mathemetic");
		questions = new ArrayList<>();

//		<!-- Question -->
		qm = new QuestionModel();
		qm.setQuestionText("6 - 1 x 0 + 2 / 2 = ?");
		choices = new ArrayList<>();
		choices.add(new SelectItem("A", "5"));
		choices.add(new SelectItem("B", "1"));
		choices.add(new SelectItem("C", "0"));
		choices.add(new SelectItem("D", "2"));
		qm.setChoices(choices);
		questions.add(qm);
		
		section.setQuestions(questions);
		sections.add(section);
		
	}
	
	public String doFinish() {
		System.out.println("Finished");
		return null;
	}
	
	public void startQuestion() {
		sectionIntro = false;
	}
	
	public void nextSection() {
		if(validateQuestionAnswer()) {
			currSectionNum++;
			currQuestionNum = 0;
			sectionIntro = true;
		}
	}
	
	private boolean validateQuestionAnswer() {
		QuestionModel q = sections.get(currSectionNum).getQuestions().get(currQuestionNum);
		if(StringUtils.isBlank(q.getAnswer())) {
			MessageUtils.getInstance().addErrorMessage("msg", "Please, answer the question.");
			return false;
		}
		return true;
	}
	
	public void nextQuestion() {
		if(validateQuestionAnswer()) {
			currQuestionNum++;
		}
	}
	
	public void previousQuestion() {
		currQuestionNum--;
	}

	public List<QuestionModel> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionModel> questions) {
		this.questions = questions;
	}

	public int getCurrQuestionNum() {
		return currQuestionNum;
	}

	public void setCurrQuestionNum(int currQuestionNum) {
		this.currQuestionNum = currQuestionNum;
	}

	public int getQuestionSize() {
		return questions.size();
	}

	public List<SectionModel> getSections() {
		return sections;
	}

	public void setSections(List<SectionModel> sections) {
		this.sections = sections;
	}

	public int getCurrSectionNum() {
		return currSectionNum;
	}

	public void setCurrSectionNum(int currSectionNum) {
		this.currSectionNum = currSectionNum;
	}

	public boolean isSectionIntro() {
		return sectionIntro;
	}

	public void setSectionIntro(boolean sectionIntro) {
		this.sectionIntro = sectionIntro;
	}
	
}
