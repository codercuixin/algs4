package search;

import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/3 11:02
 */
public class SparseVector {
    private int d; //dimension
    private ST<Integer, Double> st; //vector, represented by index-value pairs

    public SparseVector(int d){
        this.d = d;
        this.st = new ST<Integer, Double>();
    }

    public void put(int i, double value){
        if( i < 0 || i>=d) throw new IllegalArgumentException("illegal index");
        if(value == 0.0) st.delete(i);
        else st.put(i, value);
    }

    public double get(int i){
        if(i <0 || i>=d) throw new IllegalArgumentException("illegal index");
        if(st.contains(i)) return st.get(i);
        else return 0.0;
    }
    /**
     * Returns the number of nonzero entries in this vector.
     *
     * @return the number of nonzero entries in this vector
     */
    public int nnz() {
        return st.size();
    }

    /**
     * Returns the dimension of this vector.
     *
     * @return the dimension of this vector
     * @deprecated Replaced by {@link #dimension()}.
     */
    @Deprecated
    public int size() {
        return d;
    }

    /**
     * Returns the dimension of this vector.
     *
     * @return the dimension of this vector
     */
    public int dimension() {
        return d;
    }
    public double dot(SparseVector that){
        if (this.d != that.d) throw new IllegalArgumentException("Vector lengths disagree");
        double sum = 0.0;

        //iterate over the vector with the fewest nonzeros
        if(this.st.size() <= that.st.size()){
            for(int i: this.st.keys()){
                if(that.st.contains(i)){
                    sum += this.get(i) * that.get(i);
                }
            }
        }else{
            for(int i: that.st.keys()){
                if(this.st.contains(i)){
                    sum += this.get(i) * that.get(i);
                }
            }
        }
        return sum;
    }

    public double dot(double[] that){
        double sum = 0.0;
        //因为st是稀疏的键，所以用它循环次数更少。
        for(int i: st.keys()){
            sum += st.get(i) * that[i];
        }
        return sum;
    }

    /**
     * Returns the magnitude of this vector.
     * This is also known as the L2 norm or the Euclidean norm.
     *
     * @return the magnitude of this vector
     */
    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    /**
     * Returns the Euclidean norm of this vector.
     *
     * @return the Euclidean norm of this vector
     * @deprecated Replaced by {@link #magnitude()}.
     */
    @Deprecated
    public double norm() {
        return Math.sqrt(this.dot(this));
    }


    public SparseVector plus(SparseVector that){
        if (this.d != that.d) throw new IllegalArgumentException("Vector lengths disagree");
        SparseVector c = new SparseVector(this.d);
        for(int i: this.st.keys()){
            c.put(i, this.get(i));
        }
        for(int i: that.st.keys()){
            c.put(i, that.get(i) + c.get(i));
        }
        return c;
    }
    /**
     * Returns a string representation of this vector.
     * @return a string representation of this vector, which consists of the
     *         the vector entries, separates by commas, enclosed in parentheses
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i : st.keys()) {
            s.append("(" + i + ", " + st.get(i) + ") ");
        }
        return s.toString();
    }

    public static void main(String[] args){
        SparseVector a = new SparseVector(10);
        SparseVector b = new SparseVector(10);
        a.put(3, 0.50);
        a.put(9, 0.75);
        a.put(6, 0.11);
        a.put(6, 0.00);
        b.put(3, 0.60);
        b.put(4, 0.90);
        StdOut.println("a = "+a);
        StdOut.println("b = "+b);
        StdOut.println("a dot b = "+ a.dot(b));
        StdOut.println("a plus b = "+a.plus(b));
    }
}
