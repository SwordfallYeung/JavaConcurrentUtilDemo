package com.util.base.b_class;

import java.security.PermissionCollection;
import java.util.PropertyPermission;

/**
 * @author y15079
 * @create: 9/25/17 10:52 PM
 * @desc:
 */
public class PropertyPermissionExample {

    private static PropertyPermission permission;

    public static void equals(){
        // Build property permissions collection
        permission = new PropertyPermission("java.home.usr", "read");

        // Check file read,write permissions
        String path="java.home.usr";

        //Check permissions are equal
        if (permission.equals(new PropertyPermission(path,"read"))){
            System.out.println("Has permissions on "+path+" for read");
        }else{
            System.out.println("No permissions on "+path+" for read");
        }

        // Check permissions are equal
        if(permission.equals(new PropertyPermission(path, "write"))) {
            System.out.println("Has permissions on "+path+" for write");
        }else {
            System.out.println("No permissions on "+path+" for write");
        }
    }

    public static void getActions(){
        permission=new PropertyPermission("java.home.*","read,write");

        String path="java.home.usr";

        if (path.matches(permission.getName())){
            String actions=permission.getActions();
            if (actions.contains("read"))
                System.out.println("Has permissions on "+path+" for read");
            if(actions.contains("write"))
                System.out.println("Has permissions on "+path+" for write");
        }
    }

    public static void implies(){
        permission=new PropertyPermission("java.*","read,write");

        String path="java.home";

        if (permission.implies(new PropertyPermission(path,"read")))
            System.out.println("Has permissions on "+path+" for read");
        if(permission.implies(new PropertyPermission(path,"write")))
            System.out.println("Has permissions on "+path+" for write");

    }

    public static void permissionCollection(){
        PermissionCollection permissions;

        PropertyPermission permission = new PropertyPermission("java.*", "read");
        permissions = permission.newPermissionCollection();
        permissions.add(permission);
        permissions.add(new PropertyPermission("java.home.*", "read,write"));
        System.out.println(permissions);
    }

    public static void main(String[] args) {
//           getActions();
        permissionCollection();
    }


}
