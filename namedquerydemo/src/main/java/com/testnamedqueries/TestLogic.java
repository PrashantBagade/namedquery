package com.testnamedqueries;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.internal.StaticFilterAliasGenerator;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.namequeriesdemo.Employee;

public class TestLogic {

	private static org.apache.log4j.Logger logger=Logger.getLogger(TestLogic.class);
	
	public static void main(String[] args) {
		//logger.fatal("this is fatal level");
		logger.error("error level");
		
		SessionFactory sessionFactory= new AnnotationConfiguration().configure().buildSessionFactory();
		Session session= sessionFactory.openSession();
		Transaction tran=session.beginTransaction();
		
		Employee emp1=new Employee();
		emp1.setEmpName("Jaideep Mohite");
		emp1.setEmpSalary(10000);
		
		Employee emp2=new Employee();
		emp2.setEmpName("Prashant chitte");
		emp2.setEmpSalary(30000);
		
		Employee emp3=new Employee();
		emp3.setEmpName("Prashant Bagade");
		emp3.setEmpSalary(20000);
		
		session.save(emp1);
		session.save(emp2);
		session.save(emp3);
		
		SQLQuery query=session.createSQLQuery("select * from tableemployee");
		query.addEntity(Employee.class);
		
		List<Employee> result=query.list();
		System.out.println("Emp_ID      EmpName     Emp_Salary");
		for (Employee employee : result) {
		System.out.println(employee.getEmpId()+" "+employee.getEmpName()+" "+employee.getEmpSalary());	
		}
		
		
		session.flush();
		tran.commit();
		session.close();
		
		
	}
}
