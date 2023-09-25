package za.co.emerge.formgenerator.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.emerge.formgenerator.persistence.entity.PDFform;

public interface PDFformBuilderRepository extends JpaRepository<PDFform, Long>
{

}
