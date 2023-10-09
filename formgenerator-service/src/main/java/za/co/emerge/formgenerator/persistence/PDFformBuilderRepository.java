package za.co.emerge.formgenerator.persistence;

import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.emerge.formgenerator.perfomancematrix.util.PerfomanceLoggerSubject;
import za.co.emerge.formgenerator.persistence.entity.PDFform;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 *
 */
@Repository
public interface PDFformBuilderRepository extends JpaRepository<PDFform, Long>
{

	//Proxy method to persist to the Database and call performance matrix
	public default PDFform saveEntityInstance(PDFform pdfEntiy)
	{

		CompletableFuture<Long> executionTimeStart = CompletableFuture.supplyAsync(() -> 
		{
			return PerfomanceLoggerSubject.startPostgresDatabaseTimeLog();
		});
		
		pdfEntiy = this.save(pdfEntiy);
		
		executionTimeStart.thenAccept( startLogTime -> {PerfomanceLoggerSubject.endPostgresDatabaseTimeLog(startLogTime);});
		return pdfEntiy;
	}
}
