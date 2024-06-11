package br.gov.ma.tce.desafiocruddscommerce.services;

import br.gov.ma.tce.desafiocruddscommerce.dto.ClientDTO;
import br.gov.ma.tce.desafiocruddscommerce.entities.Client;
import br.gov.ma.tce.desafiocruddscommerce.repositories.ClientRepository;
import br.gov.ma.tce.desafiocruddscommerce.services.exceptions.DatabaseException;
import br.gov.ma.tce.desafiocruddscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllClients(Pageable pageable){
        Page<Client> result = clientRepository.findAll(pageable);
        return result.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO save(ClientDTO clientDTO){
        Client client = new Client();
        dtoToEntity(clientDTO, client);
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO){
        try{
            Client client = clientRepository.getReferenceById(id);
            dtoToEntity(clientDTO, client);
            client = clientRepository.save(client);
            return new ClientDTO(client);
        } catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try{
            clientRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    public void dtoToEntity(ClientDTO dto, Client client){
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }
}
