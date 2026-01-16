package com.company.employees.producer;

import com.company.employees.models.employee.Employee;
import com.company.employees.models.user.EmailDTO;
import com.company.employees.repository.EmployeeRepository;
import jakarta.activation.DataSource;
import jakarta.mail.Message;
import jakarta.mail.util.ByteArrayDataSource;
import org.openpdf.text.Document;
import org.openpdf.text.Font;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Component
public class EmployeeProducer {

    private final RabbitTemplate rabbitTemplate;
    private final JavaMailSender mailSender;
    private final EmployeeRepository repository;


    public EmployeeProducer(RabbitTemplate rabbitTemplate, JavaMailSender mailSender, EmployeeRepository repository) {
        this.rabbitTemplate = rabbitTemplate;
        this.mailSender = mailSender;
        this.repository = repository;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;
    @Value(value = "${spring.mail.username}")
    private String emailTo;

    public void sendEmail(Employee employee) {
        var emailDto = new EmailDTO();
        emailDto.setUserId(employee.getId());
        emailDto.setEmailTo(employee.getEmail());
        emailDto.setSubject("Company");
        emailDto.setText("Parábens por se juntar ao time!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

    public byte[] sendReportEmail() {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, baos);
        document.open();
        Font font = new Font(Font.HELVETICA, 18, Font.NORMAL);
        Paragraph titulo = new Paragraph("Relatório", font);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        titulo.setSpacingBefore(20);
        document.add(titulo);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);
        table.setSpacingAfter(20);

        table.addCell("Funcionário ");
        table.addCell("Contrato");
        table.addCell("Salário");
        table.addCell("E-mail");

        List<Employee> employees = repository.findAll();

        employees.forEach(e -> {
            table.addCell(e.getNome());
            table.addCell(String.valueOf(e.getTipo()));
            table.addCell(String.valueOf(e.getSalario()));
            table.addCell(e.getEmail());
        });

        document.add(table);
        document.close();

        mailSender.send(message -> {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setFrom(routingKey);
            helper.setSubject("Relatório de Funcionários");
            message.setRecipients(Message.RecipientType.TO, emailTo);
            helper.setText("Segue em anexo o relatório de funcionários.");
            helper.setSentDate(Date.from(Instant.now()));

            DataSource dataSource = new ByteArrayDataSource(
                    baos.toByteArray(),
                    "application/pdf");
            helper.addAttachment("relatorio_funcionarios.pdf", dataSource);
            mailSender.send(message);

        });
        return baos.toByteArray();
    }

}
