// IMainService.aidl
package com.example.testaidl;

import com.example.testaidl.MainObject;
interface IMainService {
    MainObject[] listFiles(String path);


    void exit();
}