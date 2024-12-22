package org.example.util.Manager;

import org.example.model.Result;
import org.example.util.AreaChecker;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "ResultManager")
@SessionScoped
public class ResultManager {

    private List<Result> resultList = new ArrayList<>();

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    private AreaChecker areaCheck = new AreaChecker();

    private Result result;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("studs");
    EntityManager em = emf.createEntityManager();


    @Resource
    private UserTransaction userTransaction;
    public void addResult(Result result){
        try{
            userTransaction.begin();
            resultList.add(result);
            em.merge(result);
            userTransaction.commit();
        } catch (Exception e){
            try {
                userTransaction.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @PostConstruct
    public void loadShots(){
        try{
            userTransaction.begin();
            em.createQuery("select s from Result s", Result.class).getResultStream().forEach(resultList::add);
            userTransaction.commit();
        }catch (Exception e){
            try{
                userTransaction.rollback();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
}
