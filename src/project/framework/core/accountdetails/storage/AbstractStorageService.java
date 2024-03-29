package project.framework.core.accountdetails.storage;

import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.storage.service.StorageServiceException;

import java.util.List;
import java.util.Optional;

public interface AbstractStorageService {
    void store(IAccount account) throws StorageServiceException;

    void update(IAccount account);

    void remove(String uniqueAccId) throws StorageServiceException;

    void addEntry(String uniqueAccId, Entry entry) throws StorageServiceException;

    Optional<IAccount> getIAccountByUniqueId(String uniqueId);

    List<IAccount> getAllAccounts();

}
