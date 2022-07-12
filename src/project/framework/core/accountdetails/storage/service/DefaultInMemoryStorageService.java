package project.framework.core.accountdetails.storage.service;

import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.model.account.TransactionType;
import project.framework.core.accountdetails.model.party.IParty;
import project.framework.core.accountdetails.storage.AbstractStorageService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultInMemoryStorageService implements AbstractStorageService {
    Map<String, IAccount> accountMap = new HashMap<>();

    @Override
    public void store(IAccount account) throws StorageServiceException {
        if (accountMap.containsKey(account.getUniqueId())) {
            throw new StorageServiceException("Account with unique id " + account.getUniqueId() + " already exists");
        }
        accountMap.put(account.getUniqueId(), account);
    }

    @Override
    public void update(IAccount account) throws StorageServiceException {
        if (!accountMap.containsKey(account.getUniqueId())) {
            throw new StorageServiceException("Account with unique id " + account.getUniqueId() + " doesn't exists");
        }
        // TODO:  review this logic
        IAccount iAccount = accountMap.get(account.getUniqueId());
//        Entry entry = new Entry();
//        entry.setTransactionType(TransactionType.OUTGOING);
//        entry.setTxAmount(account.getBalance());
//        entry.setDate(LocalDate.now());
//        iAccount.addEntry(entry);
    }

    @Override
    public void remove(String uniqueAccId) throws StorageServiceException {
        if (!accountMap.containsKey(uniqueAccId)) {
            throw new StorageServiceException("Not found, No Account with unique id " + uniqueAccId);
        }
        accountMap.remove(uniqueAccId);
    }

    @Override
    public void addEntry(String uniqueAccId, Entry entry) throws StorageServiceException {
        if (accountMap.containsKey(uniqueAccId)) {
            throw new StorageServiceException("Account with unique id " + uniqueAccId + " already exists");
        }
        IAccount account = accountMap.get(uniqueAccId);
        account.addEntry(entry);
    }

    @Override
    public Optional<IAccount> getIAccountByUniqueId(String uniqueId) {
        if (!accountMap.containsKey(uniqueId)) {
            return Optional.ofNullable(null);
        }
        return Optional.ofNullable(accountMap.get(uniqueId));
    }

    @Override
    public List<IAccount> getAllAccounts() {
        List<IAccount> accounts = accountMap.entrySet()
                .stream()
                .map(mapEntry -> mapEntry.getValue())
                .collect(Collectors.toList());
        return accounts;
    }

    @Override
    public List<IParty> getAllParties() {
        List<IParty> partyList = accountMap.entrySet()
                .stream()
                .map(mapEntry -> mapEntry.getValue().getParty())
                .distinct()
                .collect(Collectors.toList());
        return partyList;
    }
}
