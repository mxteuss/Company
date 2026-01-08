package com.company.employees.service;


import com.company.employees.models.employee.Enum.Tipo;
import com.company.employees.models.employee.Employee;
import com.company.employees.producer.EmployeeProducer;
import com.company.employees.repository.EmployeeRepository;
import jakarta.activation.DataSource;
import jakarta.mail.Message;
import jakarta.mail.util.ByteArrayDataSource;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.openpdf.text.Document;
import org.openpdf.text.Font;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {

    @Value("${spring.mail.username}")
    private String emailFrom;


    private final EmployeeRepository repository;
    private final EmployeeProducer employeeProducer;
    private final JavaMailSender mailSender;

    public EmployeeService(EmployeeRepository repository, EmployeeProducer employeeProducer, JavaMailSender mailSender) {
        this.repository = repository;
        this.employeeProducer = employeeProducer;
        this.mailSender = mailSender;
    }

    @Transactional
    public Employee save(Employee emp) {
        if (emp.getTipo().equals(Tipo.Efetivo)) {
            emp.setAdicional(null);
        }
        employeeProducer.sendEmail(emp);
        return repository.save(emp);
    }

    public void deleteById(Long id) {
         repository.deleteById(id);
    }

    public Employee uptade(Employee emp) {
        Employee novoEmp = new Employee();
        emp = novoEmp;
        if (emp.getTipo().equals(Tipo.Efetivo)) {
            emp.setAdicional(null);
        }
        return repository.save(novoEmp);
    }


    public Page<@NonNull Employee> findAll(int pageNO, int pageSize) {
        Pageable pageable = PageRequest.of(pageNO, pageSize);
        return repository.findAll(pageable);

    }

    public Optional<Employee> findById(Long id) {
        return Optional.of(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
    }


    public byte[] report() {
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

        for (Employee e : employees) {
            table.addCell(e.getNome());
            table.addCell(String.valueOf(e.getTipo()));
            table.addCell(String.valueOf(e.getSalario()));
            table.addCell(e.getEmail());
        }
        document.add(table);
        document.close();

        mailSender.send(message -> {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setFrom(emailFrom);
            helper.setSubject("Relatório de Funcionários");
            message.setRecipients(Message.RecipientType.TO, emailFrom);
            helper.setText("Segue em anexo o relatório de funcionários.");

            DataSource dataSource = new ByteArrayDataSource(
                    baos.toByteArray(),
                    "application/pdf");
            helper.addAttachment("relatorio_funcionarios.pdf", dataSource);
        });
        return baos.toByteArray();
    }
}



