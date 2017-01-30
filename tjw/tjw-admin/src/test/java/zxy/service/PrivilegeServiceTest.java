package zxy.service;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zxy.JsonPrettyUtils;
import zxy.JunitUtils;
import zxy.permission.NavPermissionPass;
import zxy.permission.entity.Resource;
import zxy.permission.PermissionService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class PrivilegeServiceTest {
    @Autowired
    private PermissionService privilegeService;
    @Autowired
    private NavPermissionPass navPermissionPass;

    @Test
    public void getResourcesForUser() {
        Integer rootUid = 1;
        List<Resource> resources = privilegeService.getResourcesForUser(rootUid);
        System.out.println("getResourcesForUser:" + JsonPrettyUtils.toString(resources));
        Assert.assertTrue(CollectionUtils.isNotEmpty(resources));
    }

    @Test
    public void pass() {
        System.out.println("===pass===");
        Assert.assertFalse(navPermissionPass.pass(null, JunitUtils.toSet(1001), 1002));
        Assert.assertFalse(navPermissionPass.pass(null, JunitUtils.toSet(1002), 1001));
        Assert.assertTrue(navPermissionPass.pass(null, JunitUtils.toSet(1001), 1001));
        Assert.assertTrue(navPermissionPass.pass(null, JunitUtils.toSet(1001), 1));
        System.out.println("===pass===");
    }

}
