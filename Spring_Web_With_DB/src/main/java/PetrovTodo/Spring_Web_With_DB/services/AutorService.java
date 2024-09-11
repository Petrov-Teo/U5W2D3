package PetrovTodo.Spring_Web_With_DB.services;

import PetrovTodo.Spring_Web_With_DB.entities.AutorePost;
import PetrovTodo.Spring_Web_With_DB.exceptions.BadRequestExeption;
import PetrovTodo.Spring_Web_With_DB.exceptions.NotFoundException;
import PetrovTodo.Spring_Web_With_DB.reposytoris.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AutorService {

    @Autowired
    private AutoreRepository autorePostRepo;


    public Page<AutorePost> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.autorePostRepo.findAll(pageable);
    }

    public AutorePost saveAutorePost(AutorePost body) {
        this.autorePostRepo.findByEmail(body.getEmail()).ifPresent(autorePost -> {
            throw new BadRequestExeption("L'email " + body.getEmail() + " è già in uso!");
        });
        body.setAvatar("http://localhost:3001/users/?name=" + body.getNome() + "+" + body.getCognome());
        this.autorePostRepo.save(body);
        return body;
    }

    public AutorePost findById(UUID autoreId) {
        return this.autorePostRepo.findById(autoreId).orElseThrow(() -> new NotFoundException(autoreId));
    }

    public AutorePost findByIdAndUpdate(UUID autoreId, AutorePost body) {
        this.autorePostRepo.findByEmail(body.getEmail()).ifPresent(autorePost -> {
            throw new BadRequestExeption("L'email " + body.getEmail() + " è già in uso!");
        });
        AutorePost found = this.findById(autoreId);
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        found.setDataDiNascita(body.getDataDiNascita());
        return this.autorePostRepo.save(found);
    }

    public void findByIdAndDelete(UUID autoreId) {
        AutorePost found = this.findById(autoreId);
        this.autorePostRepo.delete(found);
    }
}
