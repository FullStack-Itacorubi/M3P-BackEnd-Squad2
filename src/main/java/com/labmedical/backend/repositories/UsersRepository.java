    package com.labmedical.backend.repositories;

    import com.labmedical.backend.entities.Users;
    import lombok.Data;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface UsersRepository extends JpaRepository<Users, Long> {
        Users findByEmail(String email);

        boolean existsByCpfOrEmail(String cpf, String email);

        Users getUserById(Long id);
    }
