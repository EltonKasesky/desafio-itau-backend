import com.develop.challangeitaubackend.modules.transactions.dto.TransactionRequestDTO;
import com.develop.challangeitaubackend.modules.transactions.entity.TransactionEntity;
import com.develop.challangeitaubackend.modules.transactions.exception.TransactionInFutureException;
import com.develop.challangeitaubackend.modules.transactions.exception.TransactionNegativeException;
import com.develop.challangeitaubackend.modules.transactions.factory.TransactionFactory;
import com.develop.challangeitaubackend.modules.transactions.service.TransactionService;
import org.junit.jupiter.api.*;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class TransactionServiceTest {

    private TransactionService service;

    @BeforeEach
    void setUp() {
        service = new TransactionService();
        TransactionFactory.getTransactions().clear();
    }

    // ===================== Sucesso =====================

    @Test
    @DisplayName("Deve adicionar transação com sucesso")
    void shouldAddTransactionSuccessfully() {
        TransactionRequestDTO dto = new TransactionRequestDTO(100.0, OffsetDateTime.now().minusSeconds(10));

        assertDoesNotThrow(() -> service.addTransaction(dto));
        assertEquals(1, TransactionFactory.getTransactions().size());
    }

    @Test
    @DisplayName("Deve persistir os dados corretos da transação")
    void shouldPersistCorrectTransactionData() throws Exception {
        OffsetDateTime now = OffsetDateTime.now().minusSeconds(10);
        TransactionRequestDTO dto = new TransactionRequestDTO(150.0, now);

        service.addTransaction(dto);

        TransactionEntity saved = TransactionFactory.getTransactions().get(0);
        assertEquals(150.0, saved.getValor());
        assertEquals(now, saved.getDataHora());
    }

    @Test
    @DisplayName("Deve aceitar valor positivo mínimo")
    void shouldAcceptMinimalPositiveValue() {
        TransactionRequestDTO dto = new TransactionRequestDTO(0.01, OffsetDateTime.now().minusSeconds(10));

        assertDoesNotThrow(() -> service.addTransaction(dto));
    }

    @Test
    @DisplayName("Deve aceitar transação com data no passado")
    void shouldAcceptPastDate() {
        TransactionRequestDTO dto = new TransactionRequestDTO(100.0, OffsetDateTime.now().minusDays(1));

        assertDoesNotThrow(() -> service.addTransaction(dto));
    }

    @Test
    @DisplayName("Deve adicionar múltiplas transações com sucesso")
    void shouldAddMultipleTransactions() throws Exception {
        service.addTransaction(new TransactionRequestDTO(100.0, OffsetDateTime.now().minusSeconds(5)));
        service.addTransaction(new TransactionRequestDTO(200.0, OffsetDateTime.now().minusSeconds(10)));
        service.addTransaction(new TransactionRequestDTO(300.0, OffsetDateTime.now().minusSeconds(15)));

        assertEquals(3, TransactionFactory.getTransactions().size());
    }

    // ===================== Valor inválido =====================

    @Test
    @DisplayName("Deve lançar exceção quando valor for negativo")
    void shouldThrowWhenValueIsNegative() {
        TransactionRequestDTO dto = new TransactionRequestDTO(-1.0, OffsetDateTime.now().minusSeconds(10));

        assertThrows(TransactionNegativeException.class,
                () -> service.addTransaction(dto));
    }

    @Test
    @DisplayName("Deve lançar exceção quando valor for zero")
    void shouldThrowWhenValueIsZero() {
        TransactionRequestDTO dto = new TransactionRequestDTO(0.0, OffsetDateTime.now().minusSeconds(10));

        assertThrows(TransactionNegativeException.class,
                () -> service.addTransaction(dto));
    }

    @Test
    @DisplayName("Não deve salvar transação quando valor for inválido")
    void shouldNotSaveWhenValueIsInvalid() {
        TransactionRequestDTO dto = new TransactionRequestDTO(-50.0, OffsetDateTime.now().minusSeconds(10));

        assertThrows(TransactionNegativeException.class,
                () -> service.addTransaction(dto));
        assertTrue(TransactionFactory.getTransactions().isEmpty());
    }

    // ===================== Data inválida =====================

    @Test
    @DisplayName("Deve lançar exceção quando data estiver no futuro")
    void shouldThrowWhenDateIsInFuture() {
        TransactionRequestDTO dto = new TransactionRequestDTO(100.0, OffsetDateTime.now().plusSeconds(10));

        assertThrows(TransactionInFutureException.class,
                () -> service.addTransaction(dto));
    }

    @Test
    @DisplayName("Não deve salvar transação quando data for futura")
    void shouldNotSaveWhenDateIsInFuture() {
        TransactionRequestDTO dto = new TransactionRequestDTO(100.0, OffsetDateTime.now().plusDays(1));

        assertThrows(TransactionInFutureException.class,
                () -> service.addTransaction(dto));
        assertTrue(TransactionFactory.getTransactions().isEmpty());
    }

    // ===================== Combinações =====================

    @Test
    @DisplayName("Deve validar valor antes da data")
    void shouldValidateValueBeforeDate() {
        TransactionRequestDTO dto = new TransactionRequestDTO(-1.0, OffsetDateTime.now().plusSeconds(10));

        assertThrows(TransactionNegativeException.class,
                () -> service.addTransaction(dto));
    }
}