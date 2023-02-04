package com.example.homework2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // задание полей

    float astronomicaltelescop = 14_000; //стоимость астрономического телескопа
    int account = 1_000; // счёт пользовател
    float spcholarshi =2_500; // стипендия
    int percentFree = 100;
    float percentBank = 5; // годовая процентная ставка
    float[] monthlyPayments = new float[12]; // создание массива ежемесячных платежей на 1год

    // метод подсчёта стоимости астрономического телескопа с учетом средст на счету
    private float astronomicaltelescopWithContribution() {
        return astronomicaltelescop - account; // возврат подсчитанного значения
    }

    // метод подсчёта ежемесячных трат на выплаты за астрономический телескоп(стипендия, процент своб.трат)
    public float mortgageCosts(float amount, int  percent) {
        return (amount*percent)/100;
    }

    // метод подсчёта времени накопления на астрономический телескоп (стоимость, сумма платежа, годовой процент)
    // и заполнение массива monthlyPayments[] ежемесячными платежами
    public int countMonth(float total, float mortgageCosts, float percentBankYear) {

        float percentBankMonth = percentBankYear / 12; // подсчёт ежемесячного процента накоплений
        int count = 0; // счётчик месяцев выплаты за астрономический телескоп

        // алгоритм расчёта ипотеки
        while (total>0) {
            count++; // добавление нового месяца платежа
            total = (total + (total*percentBankMonth)/100) - mortgageCosts; // вычисление долга с учётом выплаты и процента
            // заполнение массива ежемесячными платежами за астрономический телескоп
            if(total > mortgageCosts) { // если сумма стоимости больше ежемесячного платежа, то
                monthlyPayments[count-1] = mortgageCosts; // в массив добавляется целый платёж
            } else { // иначе
                monthlyPayments[count-1] = total; // в массив добавляется платёж равный остатку стоимости
            }
        }

        return count;
    }

    // создание дополнительных полей для вывода на экран полученных значений
    private TextView countOut; // поле вывода количества месяцев выплаты ипотеки
    private TextView manyMonthOut; // поле выписки по ежемесячным платежам

    // вывод на экран полученных значений
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // присваивание переменным активити элементов представления activity_main
        countOut = findViewById(R.id.countOut); // вывод информации количества месяцев выплаты ипотеки
        manyMonthOut = findViewById(R.id.manyMonthOut); // вывод информации выписки по ежемесячным платежам

        // запонение экрана
        // 1) вывод количества месяцев
        countOut.setText("ежемесячная выплатыза за астрономический телескоп" + countMonth(astronomicaltelescopWithContribution(), mortgageCosts(spcholarshi,percentFree),percentBank) + " месяцев");
        // 2) подготовка выписки
        String monthlyPaymentsList = "";
        for(float list : monthlyPayments) {
            if (list > 0) {
                monthlyPaymentsList = monthlyPaymentsList + Float.toString(list) + " монет ";
            } else {
                break;
            }
        }
        // 3) вывод выписки ежемесячных
        manyMonthOut.setText("Первоначальный взнос " + account + " монет, ежемесячные выплаты за астрономический телескоп: " + monthlyPaymentsList);
    }
}