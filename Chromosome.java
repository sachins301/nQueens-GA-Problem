import java.util.*;

public class Chromosome{
    int num; //to set the number of queens
    int d; //AP.. to calculate each distance
    int score;
    ArrayList<Integer> distance = new ArrayList<Integer>(d);
    ArrayList<Integer> weight = new ArrayList<Integer>(d);
    //int distance[] = new int[6];
    //int weight[] = new int[6];
    float merit;
    float relativeMerit;
    ArrayList<Integer> genes = new ArrayList<Integer>(num);

    Chromosome(int n){
        num = n;
        d = ((n-1)*(2 + (n - 2)))/2;
        //setDistance();
        //setWeight();
        //setScore();
            
    }

    Chromosome(){
        num = 4;
        d = ((num-1)*(2 + (num - 2)))/2;

        for(int i=0; i<num; i++)
            genes.add(0);

        setDistance();
        setWeight();
        setScore();
    }

    //Set the values of each gene of a chromosome
    public void setGenes(ArrayList<Integer> G){
        genes = G;
        setDistance();
        setWeight();
        setScore();
    }

    public void setGenesByPos(int val, int pos){
        genes.set(pos,val);
    }

    public void reCalculate(){
        setDistance();
        setWeight();
        setScore();
    }

    public ArrayList<Integer> getGenes() {
        return genes;
    }

    public void display(){
        System.out.print(genes);
        System.out.print("\t\tScore: "+score+"\t\tMerit: "+merit+"\t\tRelative merit: "+relativeMerit+"\n");
    }

    public void setDistance(){
        int dist;
        for(int i=0; i<num; i++){
            for(int j=i+1; j<num; j++) {
                dist = (j-i) + Math.abs(genes.get(i)-genes.get(j));
                distance.add(dist);
            }
        }
    }

    /*Weight is being set after checking whether 
    a clash occurs horizontally or diagonally
    This part needs review*/
    public void setWeight(){

        for(int i=0; i<num; i++)
            for(int j=i+1; j<num; j++){
                if(genes.get(i) == genes.get(j) || genes.get(i) == genes.get(j)-(j-i) || genes.get(i) == genes.get(j)+(j-i))
                    weight.add(10);
                else
                    weight.add(20);
            }
    }

    public void setScore(){

        score=0;

        for(int i=0; i<d; i++)
            score += distance.get(i)*weight.get(i);
    }

    public void setMerit(int totalScore) {
        merit = (score/(float)totalScore);
    }

    public float getMerit(){
        return merit;
    }

    public void setRelativeMerit(float totalMerit){
        relativeMerit = merit*100/totalMerit;
    }

    public float getRelativeMerit(){
        return relativeMerit;
    }
}



