package com.mathews.steps;

import com.mathews.pages.CalculatorPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.net.MalformedURLException;

import static com.mathews.core.BasePage.takeScreenshot;

public class CalculadoraSteps {

    private int primeiroNumero;
    private int segundoNumero;

    private CalculatorPage calculatorPage = new CalculatorPage();

    @Dado("que acesso a calculadora")
    public void que_acesso_a_calculadora() {
        calculatorPage.getWait(5);
    }

    @Quando("clico no numero {int}")
    public void clico_no_numero(int int1) throws MalformedURLException {
        this.primeiroNumero = int1;
        calculatorPage.typeFirstNumber(String.valueOf(int1));
        takeScreenshot();
    }

    @Quando("clico novamente no numero {int}")
    public void clico_novamente_no_numero(int int2) throws MalformedURLException {
        this.segundoNumero = int2;
        calculatorPage.typeSecondNumber(String.valueOf(int2));
        takeScreenshot();
    }

    @Quando("clico no botão de soma")
    public void clico_no_botao_de_soma() throws MalformedURLException {
        calculatorPage.sum();
        takeScreenshot();
    }

    @Entao("valido que a soma dos numeros é igual a {int}")
    public void valido_que_a_soma_dos_numeros_e_igual_a(Integer int1) throws MalformedURLException {
        int resultado = Integer.parseInt(calculatorPage.getResult());

        Assert.assertEquals((primeiroNumero + segundoNumero), resultado);
        takeScreenshot();
    }
}
