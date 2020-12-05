package clients;

public class Pair <T1 extends Comparable<T1>, T2 extends Comparable<T2>> implements Comparable<Pair<T1, T2>> {
	
	public T1 a;    	// a = a
	public T2 b;	      // marks = b
	
	public Pair(T1 a, T2 b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair<T1, T2> other = (Pair<T1, T2>) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		return true;
	}
	
	public T1 getA() {
		return a;
	}

	public void setA(T1 a) {
		this.a = a;
	}

	public T2 getB() {
		return b;
	}

	public void setB(T2 b) {
		this.b = b;
	}

	@Override
	public int compareTo(Pair<T1, T2> that )
	{
		int  cmp = that.getB().compareTo(this.getB());
		if( cmp == 0)
			cmp = that.getA().compareTo(this.getA());
		return cmp;
	}
}
