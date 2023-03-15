package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import pages.CalculatorPage;

public class CalculadoraSteps {

    private int primeiroNumero;
    private int segundoNumero;

    private CalculatorPage calculatorPage = new CalculatorPage();

    @Dado("que acesso a calculadora")
    public void que_acesso_a_calculadora() {
        calculatorPage.closeKeyboard();
        calculatorPage.getWait(5);
    }

    @Quando("clico no numero {int}")
    public void clico_no_numero(int int1) {
        this.primeiroNumero = int1;
        calculatorPage.typeFirstNumber(String.valueOf(int1));
    }

    @Quando("clico novamente no numero {int}")
    public void clico_novamente_no_numero(int int2) {
        this.segundoNumero = int2;
        calculatorPage.typeSecondNumber(String.valueOf(int2));
    }

    @Quando("clico no botão de soma")
    public void clico_no_botao_de_soma() {
        calculatorPage.sum();
    }

    @Entao("valido que a soma dos numeros é igual a {int}")
    public void valido_que_a_soma_dos_numeros_e_igual_a(Integer int1) {
        int resultado = Integer.parseInt(calculatorPage.getResult());

        Assert.assertEquals((primeiroNumero + segundoNumero), resultado);
    }
}
