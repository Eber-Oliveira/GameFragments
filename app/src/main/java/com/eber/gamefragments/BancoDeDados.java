package com.eber.gamefragments;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Questoes.class}, version = 1)
public abstract class BancoDeDados extends RoomDatabase{
    //Recuperar o DAO
    public abstract DAO getDAO();

    // Instancia unica para o Banco de Daoos
    private static BancoDeDados INSTANCE;

    //Modelo Singleton
    public static BancoDeDados getBancoDeDados(final Context context) {
        if(INSTANCE==null){
            synchronized (BancoDeDados.class){
                if(INSTANCE==null){
                    //Cria o Banco de Dados
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BancoDeDados.class,
                            "bd_questoes").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

}
