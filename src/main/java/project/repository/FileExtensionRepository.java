package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.entity.FileExtension;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileExtensionRepository extends JpaRepository<FileExtension, Long> {

    List<FileExtension> findAllByIsDefaultTrue();
    
    Optional<FileExtension> findByExtension(String extension);
    
    @Query("SELECT COUNT(f) > 0 FROM FileExtension f WHERE f.id = :id")
    boolean existsById(@Param("id") Long id);
}
