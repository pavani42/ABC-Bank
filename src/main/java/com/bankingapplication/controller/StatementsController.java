package com.bankingapplication.controller;

import com.bankingapplication.model.Statements;
import com.bankingapplication.service.StatementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.MediaType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/accounts/{accountId}/statements")
public class StatementsController {

    private final StatementsService statementsService;

    @Autowired
    public StatementsController(StatementsService statementsService) {
        this.statementsService = statementsService;
    }

    @GetMapping
    public ResponseEntity<List<Statements>> getAllStatements(@PathVariable Long accountId) {
        List<Statements> statements = statementsService.getAllStatementsByAccountId(accountId);
        return ResponseEntity.ok(statements);
    }

    @GetMapping("/{statementId}")
    public ResponseEntity<Statements> getStatementById(@PathVariable Long accountId, @PathVariable Long statementId) {
        Statements statement = statementsService.getStatementById(accountId, statementId);
        return ResponseEntity.ok(statement);
    }

    @PostMapping
    public ResponseEntity<Statements> createStatement(@PathVariable Long accountId, @RequestBody Statements statements) {
        Statements createdStatement = statementsService.createStatement(accountId, statements);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStatement);
    }

    @DeleteMapping("/{statementId}")
    public ResponseEntity<Void> deleteStatement(@PathVariable Long accountId, @PathVariable Long statementId) {
        statementsService.deleteStatement(accountId, statementId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadStatements(@PathVariable Long accountId, @RequestParam String format) throws IOException {
        List<Statements> statements = statementsService.getAllStatementsByAccountId(accountId);

        byte[] data;
        String contentType;
        String fileExtension;

        if ("pdf".equalsIgnoreCase(format)) {
            data = generatePdf(statements);
            contentType = "application/pdf";
            fileExtension = ".pdf";
        } else if ("csv".equalsIgnoreCase(format)) {
            data = generateCsv(statements);
            contentType = "text/csv";
            fileExtension = ".csv";
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=statements" + fileExtension)
                .contentType(MediaType.parseMediaType(contentType))
                .body(data);
    }

    private byte[] generatePdf(List<Statements> statements) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);
        document.add(new Paragraph("Statements"));

        Table table = new Table(3);
        table.addCell(new Cell().add(new Paragraph("Account ID")));
        table.addCell(new Cell().add(new Paragraph("Statement Date")));
        table.addCell(new Cell().add(new Paragraph("Statement Details")));

        for (Statements statement : statements) {
            table.addCell(new Cell().add(new Paragraph(statement.getAccountId().toString())));
            table.addCell(new Cell().add(new Paragraph(statement.getStatementDate().toString())));
            table.addCell(new Cell().add(new Paragraph(statement.getStatementDetails())));
        }

        document.add(table);
        document.close();

        return out.toByteArray();
    }

    private byte[] generateCsv(List<Statements> statements) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter printer = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withHeader("Account ID", "Statement Date", "Statement Details"));

        for (Statements statement : statements) {
            printer.printRecord(statement.getAccountId(), statement.getStatementDate(), statement.getStatementDetails());
        }

        printer.flush();
        return out.toByteArray();
    }
}