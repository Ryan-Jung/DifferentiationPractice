package simpleDifferentiation.rj;
/**
 * Keeps track of a term's constant and its power of x.
 * 
 */
public class Term<T extends Number> {
	private T constant;
	private T power;
	
	Term(T constant, T power){
		setConstant(constant);
		setPower(power);
	}
	
	public void setConstant(T constant){
		this.constant = constant;
	}
	public void setPower(T power){
		this.power = power;
	}
	public T getConstant(){
		return constant;
	}
	public T getPower(){
		return power;
	}
	
	public String toString(){
		if(getPower().equals(0)){
			return constant.toString();
		}
		return constant + "x^" +  power;
	}
}
