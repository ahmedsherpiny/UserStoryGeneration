package userStory.persona;


public abstract class PersonaltyCriteria{
	public int id;
	public final static int min = 1;
	public final static int max = 10;
	public int criteriaValue;
	
	public void setCriteriaValue(int value)
	{
		if (value<min)
			this.criteriaValue=min;
		else if (value>max)
			this.criteriaValue=max;
		else
			this.criteriaValue=value;
	}
	
	public int getCriteriaValue()
	{
		return criteriaValue;
	}
	
	public void setId(int id)
	{
		this.id =id;
	}
	
	public int getId()
	{
		return id;
	}
}
