package com.taskapp.dataaccess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.taskapp.model.User;

public class UserDataAccess {
    private final String filePath;

    public UserDataAccess() {
        filePath = "app/src/main/resources/users.csv";
    }

    /**
     * 自動採点用に必要なコンストラクタのため、皆さんはこのコンストラクタを利用・削除はしないでください
     * @param filePath
     */
    public UserDataAccess(String filePath) {
        this.filePath = filePath;
    }

    /**
     * メールアドレスとパスワードを基にユーザーデータを探します。
     * @param email メールアドレス
     * @param password パスワード
     * @return 見つかったユーザー
     */
    
    //パスワードとメールアドレスが一致したらインスタンス化して返す。
    public User findByEmailAndPassword(String email, String password) {
        User users = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (!(values[2].equals(email) && values[3].equals(password))) {
                    continue;
                }
                int code = Integer.parseInt(values[0]);
                String name = values[1];
                String mail = values[2];
                String pass= values[3];
                users = new User(code, name, mail, pass);
            }
        } catch (IOException e) {
            e.printStackTrace();;
        }
        return users;
    }

    /**
     * コードを基にユーザーデータを取得します。
     * @param code 取得するユーザーのコード
     * @return 見つかったユーザー
     */
    public User findByCode(int num) {
        User user = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                //コードとRepUserが一致したら返す。
                if (Integer.parseInt(values[0]) ==  num) {
                    int code = Integer.parseInt(values[0]);
                    String name = values[1];
                    String email = values[2];
                    String password = values[3];
                    user = new User(code, name, email, password);
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
