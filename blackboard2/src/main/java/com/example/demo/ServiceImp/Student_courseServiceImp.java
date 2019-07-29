package com.example.demo.ServiceImp;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Dao.Student_courseDao;
import com.example.demo.Domain.Attendance;
import com.example.demo.Domain.Student_course;
import com.example.demo.Service.Student_courseService;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.stereotype.Service;

@Service
public class Student_courseServiceImp implements Student_courseService {
	
	@Autowired
	public Student_courseDao studentCourseDao;

	@Override
	public Student_course findone(long stcsId) {
		Student_course student_course = null;
		Optional<Student_course> opstudentCourse =studentCourseDao.findById(stcsId); 
		if(opstudentCourse.isPresent()) {
			student_course = opstudentCourse.get();
		}
		return (student_course != null) ? student_course : null;
	}

	@Override
	public Student_course create(Student_course student_course) {
		studentCourseDao.save(student_course);
		return (student_course != null) ? student_course : null;
	}

	@Override
	public List<Student_course> findAll() {
		List<Student_course> list = new ArrayList<Student_course>();
		list =   (List<Student_course>) studentCourseDao.findAll();
		return list;
	}

	@Override
	public List<Student_course> findByCourseId(long id) {
		List<Student_course> list = new ArrayList<Student_course>();
		list =   (List<Student_course>) studentCourseDao.findByCourseId(id);
		return list;
	}

	@Override
	public Student_course findByUuid(String uuid) {
		Student_course student_course = null;
		Optional<Student_course> opstudentCourse =Optional.ofNullable(studentCourseDao.findByUuid(uuid));
		if(opstudentCourse.isPresent()) {
			student_course = opstudentCourse.get();
		}
		return (student_course != null) ? student_course : null;
	}

	@Override
	public byte[] StudentsDetailsPDF(List<Student_course> scourse, String name) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try{
			Document document = new Document(PageSize.A4);
			PdfWriter writer = PdfWriter.getInstance(document, bos);
			document.open();
			
			
			// create 4 column table
			PdfPTable table = new PdfPTable(3);
		    PdfPTable table1=new PdfPTable(3);

			// set the width of the table to 100% of page
			table.setWidthPercentage(100);
//			table1.setWidthPercentage(100);
			// set relative columns width
			table.setWidths(new int[] { 1, 2,1});
			table1.setWidths(new int[] { 1, 2,1});

			// ----------------Table Header "Title"----------------
			// font
			Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
			Font font2 = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
			// create header cell
			Chunk title=new Chunk("                                                         Course  Report",font);
			//title.setUnderline(0.3f, -2f); //0.1 thick, -2 y-location
			
			
			Student_course h=new Student_course();

			if(scourse.size()>0) {
				h=scourse.get(0);
			}
			
			document.add(title);
			
			document.add(new Paragraph(" "));
			
			Chunk title1=new Chunk("                                                         "+h.getCourse().getCourseName(),font);
			title.setUnderline(0.3f, -2f); //0.1 thick, -2 y-location
			
			document.add(title1);

			document.add(new Paragraph(" "));

			Chunk title2=new Chunk("         DATE: "+new Date(),font2);
			// title2.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
			document.add(title2);
			
			PdfPCell cell = new PdfPCell(new Phrase("Course Group:"+" "+h.getCourse().getCourseGroup(), font2));
			PdfPCell cell2 = new PdfPCell(new Phrase("Course Code: "+h.getCourse().getCourseCode(), font2));
		
			// set Column span "1 cell = 6 cells width"

			//document.add(new Paragraph(" "));

		cell.setColspan(2);
		cell.setPaddingBottom(new Float(10));
		cell2.setColspan(2);

	  
		cell2.setPaddingBottom(new Float(10));
			// set style
			// Style.headerCellStyle(cell);
		// add to table
		cell.setBorder(PdfPCell.NO_BORDER);
		cell2.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell);
		table1.addCell(cell2);

		

//		---------------Making Harvest information table-------------

		
		table1.addCell(createLabelCellDetails("Names:")).setBorderColor(BaseColor.BLACK);
	    table1.addCell(createLabelCellDetails("Student ID")).setBorderColor(BaseColor.BLACK);
	    table1.addCell(createLabelCellDetails("Marks")).setBorderColor(BaseColor.BLACK);
	   
	    
	    
	    for(Student_course ha:scourse) {
	   
	    //T-Data
	    table1.addCell(createValueCell(" "+ha.getStudent().getFirstname()));
	    table1.addCell(createValueCell(" "+ha.getStudent().getStudentId()));
	    table1.addCell(createValueCell(" "+ha.getMarks()));
	   
	    }
	    
		document.add(table1);
		document.add(new Paragraph(" "));
      document.add(new Paragraph("Printed by : "+name));

		document.close();
		//	document.add(table);
			
			
			
		}catch(Exception ex){
	         ex.printStackTrace();
		}
		
		return bos.toByteArray();
		
	

}

	
	
	//create cells
		private static PdfPCell createLabelCellDetails(String text) {
		 // font
		 Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.DARK_GRAY);

		 // create cell
		 PdfPCell cell = new PdfPCell(new Phrase(text, font));

		 // set style
		 // Style.labelCellStyle(cell);
		//cell.setBorder(PdfPCell.NO_BORDER);rgb()
		cell.setBackgroundColor(new BaseColor(237, 240, 244));
		cell.setPadding(new Float(6));
		 return cell;
		}
		
		//create cells
		private static PdfPCell createValueCell(String text) {
		 // font
		 Font font = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);

		 // create cell
		 PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setPadding(new Float(6));
		 return cell;
			
		}

}
