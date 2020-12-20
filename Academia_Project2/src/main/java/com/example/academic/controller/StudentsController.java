package com.example.academic.controller;


import com.example.academic.bean.Student_Payment;
import com.example.academic.bean.Students;
import com.example.academic.service.StudentsService;
import com.example.academic.table_out;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.List;

@Path("students")
public class StudentsController {

    StudentsService studentsService = new StudentsService();
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginStudent(Students student) throws URISyntaxException {
        Students result = studentsService.verifyEmail(student);
        if(result == null){
            return Response.noContent().build();
        }
        return Response.ok().entity(result).build();

    }
    @POST
    @Path("/getpayment")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getstudentpayments(Students student) throws URISyntaxException {
        List<Student_Payment> result = studentsService.getStuPayment(student);

        if(result == null)
        {
            return Response.noContent().build();
        }
        System.out.println(result.size());
        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/gettotalpayment")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getstudenttotalpayments(Students student) throws URISyntaxException {
        List<table_out> result = studentsService.sumPayment(student);

        if(result == null)
        {
            return Response.noContent().build();
        }

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/getduepayment")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getstudentduepayments(Students student) throws URISyntaxException {
        List<table_out> result = studentsService.duePayment(student);
        if(result == null)
        {
            return Response.noContent().build();
        }
        return Response.ok().entity(result).build();
    }



}
