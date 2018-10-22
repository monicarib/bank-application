package services.impl;

import com.bank.bank.BankApplicationTests;
import entities.Account;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import repositories.AccountRepository;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@SpringBootTest(classes = WithdrawService.class)
@RunWith(SpringRunner.class)
public class WithdrawServiceTest  {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private WithdrawService withdrawService;


    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Account account;

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void withdraw_WhenThereIsNotEnoughMoneyShouldThrowException() {
        // Given
        account= new Account("1", 0);
        when(accountService.findById(account.getId())).thenReturn(Optional.of(account));
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Balance insufficient");
        // Then
        withdrawService.withdraw(account.getId(), 2);



    }

    @Test
    public void withdraw_WhenTheAccountDoesNotExistShouldThrowException() {
        // GIVEN
        account= new Account("1", 0);
        Optional<Account> optionalAccount= Optional.empty();
        when(accountService.findById(account.getId())).thenReturn(optionalAccount);
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Account not found");

        //Then
        withdrawService.withdraw(account.getId(),2);

    }

    @Test
    public void withdraw_WhenThereIsEnoughMoneyTheBalanceShouldBeCorrect() {
        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);
        account= new Account("1", 200);
        Account accountEsperada = new Account("1",200);

        when(accountService.findById(account.getId())).thenReturn(Optional.of(account));
        when(accountService.update(account)).thenReturn(accountEsperada);

        Account accountReceived = withdrawService.withdraw(account.getId(),100);

        verify(accountService).update(accountArgumentCaptor.capture());
        assertEquals(accountArgumentCaptor.getValue().getSaldo(), Integer.valueOf(100));
    }
}