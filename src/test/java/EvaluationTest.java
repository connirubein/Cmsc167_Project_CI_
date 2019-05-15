import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EvaluationTest {
    Evaluation evaluation;

    @Test
    public void inToPre() {
        evaluation = new Evaluation("( 5 + 10 ) / ( 20 / 4 )");
        assertEquals(evaluation.inToPre(),"/+510/204");
    }

    @Test
    public void infix_evaluate_expression(){
        evaluation = new Evaluation("( 5 + 10 ) / ( 20 / 4 )");
        evaluation.two_inToPre();
        evaluation.infix_evaluate_expression(evaluation.linkedList);
        assertEquals(evaluation.answer,"3");
    }

    @Test
    public void prefix_evaluate_expression(){
        evaluation = new Evaluation("+ 9 * 2 6");
        evaluation.prefix_evaluate_expression(evaluation.string_to_list(evaluation.input));
        assertEquals(evaluation.answer,"21");
    }

    @Test
    public void postfix_evaluate_expression(){
        evaluation = new Evaluation("6 2 3 + - 3 8 2 / + * 2 ^ 3 +");
        evaluation.postfix_evaluate_expression(evaluation.string_to_list(evaluation.input));
        assertEquals(evaluation.answer,"52");
    }
}
