package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("deprecation")
@Entity
@Table(name="lutbl_question_bank")
public class QuestionBank {

	   @Id
	   @Column(name="question_id",columnDefinition = "VARCHAR(8)")
	   private String QuestionId;
	   @Column(name="class_id",columnDefinition = "VARCHAR(8)")
	   private String ClassId;
	   @Column(name="subject_id",columnDefinition = "VARCHAR(8)")
	   private String SubjectId;
	   @Column(name="question_type_id",columnDefinition = "VARCHAR(8)")
	   private String QuestionTypeId;	
	   @Column(name="question",columnDefinition = "VARCHAR(255)")
	   private String Question;
	   @Column(name="question_path",columnDefinition = "VARCHAR(255)")
	   private String QuestionPath;
	   @Column(name="opta",columnDefinition = "VARCHAR(45)")
	   private String OptionA;
	   @Column(name="optb",columnDefinition = "VARCHAR(45)")
	   private String OptionB;
	   @Column(name="optc",columnDefinition = "VARCHAR(45)")
	   private String OptionC;
	   @Column(name="optd",columnDefinition = "VARCHAR(45)")
	   private String OptionD;
	   @Column(name="ans",columnDefinition = "VARCHAR(255)")
	   private String Answer;
	   @Column(name="ans_path",columnDefinition = "VARCHAR(255)")
	   private String AnswerPath;
	   @Column(name="lsoa",columnDefinition = "VARCHAR(45)")
	   private String LeftSideOptiona;
	   @Column(name="lsob",columnDefinition = "VARCHAR(45)")
	   private String LeftSideOptionb;
	   @Column(name="lsoc",columnDefinition = "VARCHAR(45)")
	   private String LeftSideOptionc;
	   @Column(name="lsod",columnDefinition = "VARCHAR(45)")
	   private String LeftSideOptiond;
	   @Column(name="lsoe",columnDefinition = "VARCHAR(45)")
	   private String LeftSideOptione;
	   @Column(name="rso1",columnDefinition = "VARCHAR(45)")
	   private String RightSideOption1;
	   @Column(name="rso2",columnDefinition = "VARCHAR(45)")
	   private String RightSideOption2;
	   @Column(name="rso3",columnDefinition = "VARCHAR(45)")
	   private String RightSideOption3;
	   @Column(name="rso4",columnDefinition = "VARCHAR(45)")
	   private String RightSideOption4;
	   @Column(name="rso5",columnDefinition = "VARCHAR(45)")
	   private String RightSideOption5;
	   @Column(name="ans_mtfa",columnDefinition = "VARCHAR(45)")
	   private String AnswerMatchForOptiona;
	   @Column(name="ans_mtfb",columnDefinition = "VARCHAR(45)")
	   private String AnswerMatchForOptionb;
	   @Column(name="ans_mtfc",columnDefinition = "VARCHAR(45)")
	   private String AnswerMatchForOptionc;
	   @Column(name="ans_mtfd",columnDefinition = "VARCHAR(45)")
	   private String AnswerMatchForOptiond;
	   @Column(name="ans_mtfe",columnDefinition = "VARCHAR(45)")
	   private String AnswerMatchForOptione;
	   @Column(name="is_soft_delete",columnDefinition="int(1)")
	   private int isSoftDelete;
	   @Column(name="inserted_by",columnDefinition="VARCHAR(45)")
	   private String insertedBy;
	   @Column(name="inserted_time")
	   private Date insertedTime;
	   @Column(name="updated_by",columnDefinition="VARCHAR(45)")
	   private String updatedBy;
	   @Column(name="updated_time")
	   private Date updatedTime;
	   @javax.persistence.Transient
		private String message;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
   		public String getQuestionId() {
			return QuestionId;
		}
		@JsonProperty("QuestionId")
		public void setQuestionId(String questionId) {
			QuestionId = questionId;
		}
		public String getClassId() {
			return ClassId;
		}
		@JsonProperty("ClassId")
		public void setClassId(String classId) {
			ClassId = classId;
		}
		public String getSubjectId() {
			return SubjectId;
		}
		@JsonProperty("SubjectId")
		public void setSubjectId(String subjectId) {
			SubjectId = subjectId;
		}
		public String getQuestionTypeId() {
			return QuestionTypeId;
		}
		@JsonProperty("QuestionTypeId")
		public void setQuestionTypeId(String questionTypeId) {
			QuestionTypeId = questionTypeId;
		}
		public String getQuestion() {
			return Question;
		}
		@JsonProperty("Question")
		public void setQuestion(String question) {
			Question = question;
		}
		public String getQuestionPath() {
			return QuestionPath;
		}
		@JsonProperty("QuestionPath")
		public void setQuestionPath(String questionPath) {
			QuestionPath = questionPath;
		}
		public String getOptionA() {
			return OptionA;
		}
		@JsonProperty("OptionA")
		public void setOptionA(String optionA) {
			OptionA = optionA;
		}
		public String getOptionB() {
			return OptionB;
		}
		@JsonProperty("OptionB")
		public void setOptionB(String optionB) {
			OptionB = optionB;
		}
		public String getOptionC() {
			return OptionC;
		}
		@JsonProperty("OptionC")
		public void setOptionC(String optionC) {
			OptionC = optionC;
		}
		public String getOptionD() {
			return OptionD;
		}
		@JsonProperty("OptionD")
		public void setOptionD(String optionD) {
			OptionD = optionD;
		}
		public String getAnswer() {
			return Answer;
		}
		@JsonProperty("Answer")
		public void setAnswer(String answer) {
			Answer = answer;
		}
		public String getAnswerPath() {
			return AnswerPath;
		}
		@JsonProperty("AnswerPath")
		public void setAnswerPath(String answerPath) {
			AnswerPath = answerPath;
		}
	public String getLeftSideOptiona() {
		return LeftSideOptiona;
	}
	@JsonProperty("LeftSideOptiona")
	public void setLeftSideOptiona(String leftSideOptiona) {
		LeftSideOptiona = leftSideOptiona;
	}
	public String getLeftSideOptionb() {
		return LeftSideOptionb;
	}
	@JsonProperty("LeftSideOptionb")
	public void setLeftSideOptionb(String leftSideOptionb) {
		LeftSideOptionb = leftSideOptionb;
	}
	public String getLeftSideOptionc() {
		return LeftSideOptionc;
	}
	@JsonProperty("LeftSideOptionc")
	public void setLeftSideOptionc(String leftSideOptionc) {
		LeftSideOptionc = leftSideOptionc;
	}
	public String getLeftSideOptiond() {
		return LeftSideOptiond;
	}
	@JsonProperty("LeftSideOptiond")
	public void setLeftSideOptiond(String leftSideOptiond) {
		LeftSideOptiond = leftSideOptiond;
	}
	public String getLeftSideOptione() {
		return LeftSideOptione;
	}
	@JsonProperty("LeftSideOptione")
	public void setLeftSideOptione(String leftSideOptione) {
		LeftSideOptione = leftSideOptione;
	}
	public String getRightSideOption1() {
		return RightSideOption1;
	}
	@JsonProperty("RightSideOption1")
	public void setRightSideOption1(String rightSideOption1) {
		RightSideOption1 = rightSideOption1;
	}
	public String getRightSideOption2() {
		return RightSideOption2;
	}
	@JsonProperty("RightSideOption2")
	public void setRightSideOption2(String rightSideOption2) {
		RightSideOption2 = rightSideOption2;
	}
	public String getRightSideOption3() {
		return RightSideOption3;
	}
	@JsonProperty("RightSideOption3")
	public void setRightSideOption3(String rightSideOption3) {
		RightSideOption3 = rightSideOption3;
	}
	public String getRightSideOption4() {
		return RightSideOption4;
	}
	@JsonProperty("RightSideOption4")
	public void setRightSideOption4(String rightSideOption4) {
		RightSideOption4 = rightSideOption4;
	}
	public String getRightSideOption5() {
		return RightSideOption5;
	}
	@JsonProperty("RightSideOption5")
	public void setRightSideOption5(String rightSideOption5) {
		RightSideOption5 = rightSideOption5;
	}
	public String getAnswerMatchForOptiona() {
		return AnswerMatchForOptiona;
	}
	@JsonProperty("AnswerMatchForOptiona")
	public void setAnswerMatchForOptiona(String answerMatchForOptiona) {
		AnswerMatchForOptiona = answerMatchForOptiona;
	}
	public String getAnswerMatchForOptionb() {
		return AnswerMatchForOptionb;
	}
	@JsonProperty("AnswerMatchForOptionb")
	public void setAnswerMatchForOptionb(String answerMatchForOptionb) {
		AnswerMatchForOptionb = answerMatchForOptionb;
	}
	public String getAnswerMatchForOptionc() {
		return AnswerMatchForOptionc;
	}
	@JsonProperty("AnswerMatchForOptionc")
	public void setAnswerMatchForOptionc(String answerMatchForOptionc) {
		AnswerMatchForOptionc = answerMatchForOptionc;
	}
	public String getAnswerMatchForOptiond() {
		return AnswerMatchForOptiond;
	}
	@JsonProperty("AnswerMatchForOptiond")
	public void setAnswerMatchForOptiond(String answerMatchForOptiond) {
		AnswerMatchForOptiond = answerMatchForOptiond;
	}
	public String getAnswerMatchForOptione() {
		return AnswerMatchForOptione;
	}
	@JsonProperty("AnswerMatchForOptione")
	public void setAnswerMatchForOptione(String answerMatchForOptione) {
		AnswerMatchForOptione = answerMatchForOptione;
	}
	public int getIsSoftDelete() {
		return isSoftDelete;
	}
	public void setIsSoftDelete(int isSoftDelete) {
		this.isSoftDelete = isSoftDelete;
	}
	public String getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	   
	   
	   	      
}
