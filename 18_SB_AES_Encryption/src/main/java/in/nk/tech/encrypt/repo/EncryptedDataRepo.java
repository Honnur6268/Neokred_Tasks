package in.nk.tech.encrypt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nk.tech.encrypt.entity.EncryptedData;

public interface EncryptedDataRepo extends JpaRepository<EncryptedData, Integer> {

}
