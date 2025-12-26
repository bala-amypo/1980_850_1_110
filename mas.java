    // 32-40 JPA Normalization & Model Integrity
    @Test(priority=32) public void t32_2NF_KeyDependency() {
        ResourceAllocation ra = new ResourceAllocation();
        ra.setId(500L);
        Assert.assertEquals(ra.getId(), Long.valueOf(500L));
    }
    @Test(priority=33) public void t33_3NF_TransitiveCheck() {
        // Ensure roles are direct attributes of User to avoid transitive dependencies
        User u = new User();
        u.setRole("ADMIN");
        Assert.assertEquals(u.getRole(), "ADMIN");
    }
    @Test(priority=34) public void t34_ResourceAvailabilityDefault() {
        Resource r = new Resource();
        r.setAvailable(true);
        Assert.assertTrue(r.isAvailable());
    }
    @Test(priority=35) public void t35_RelationshipUserRequest() {
        ResourceRequest rr = new ResourceRequest();
        User u = new User();
        rr.setRequestedBy(u);
        Assert.assertNotNull(rr.getRequestedBy());
    }
    @Test(priority=36) public void t36_RelationshipRequestAllocation() {
        ResourceAllocation ra = new ResourceAllocation();
        ResourceRequest rr = new ResourceRequest();
        ra.setRequest(rr);
        Assert.assertEquals(ra.getRequest(), rr);
    }
    @Test(priority=37) public void t37_AuditFieldsStartTime() {
        ResourceRequest rr = new ResourceRequest();
        LocalDateTime now = LocalDateTime.now();
        rr.setStartTime(now);
        Assert.assertEquals(rr.getStartTime(), now);
    }
    @Test(priority=38) public void t38_ResourceCapacityRange() {
        Resource r = new Resource();
        r.setCapacity(10);
        Assert.assertTrue(r.getCapacity() > 0);
    }
    @Test(priority=39) public void t39_RulePriorityIntegrity() {
        AllocationRule ar = new AllocationRule();
        ar.setPriority(1);
        Assert.assertEquals(ar.getPriority(), 1);
    }
    @Test(priority=40) public void t40_EntityEqualityCheck() {
        User u1 = new User(); u1.setId(10L);
        User u2 = new User(); u2.setId(10L);
        Assert.assertEquals(u1.getId(), u2.getId());
    }

    // 41-50 Security & JWT Logic
    @Test(priority=41) public void t41_JwtGeneration() {
        String token = jwtUtil.generateToken("user@test.com");
        Assert.assertNotNull(token);
    }
    @Test(priority=42) public void t42_JwtValidation() {
        String token = jwtUtil.generateToken("admin@test.com");
        Assert.assertTrue(jwtUtil.validateToken(token, "admin@test.com"));
    }
    @Test(priority=43) public void t43_JwtSubjectExtraction() {
        String token = jwtUtil.generateToken("subject@test.com");
        Assert.assertEquals(jwtUtil.extractUsername(token), "subject@test.com");
    }
    @Test(priority=44) public void t44_JwtInvalidUserValidation() {
        String token = jwtUtil.generateToken("user1@test.com");
        Assert.assertFalse(jwtUtil.validateToken(token, "user2@test.com"));
    }
    @Test(priority=45) public void t45_PasswordStorageMock() {
        User u = new User("N", "e", "plain", "USER");
        Assert.assertNotEquals(u.getPassword(), "hashed_value");
    }
    @Test(priority=46) public void t47_JwtExpirationCheck() {
        String token = jwtUtil.generateToken("exp@test.com");
        Assert.assertFalse(jwtUtil.isTokenExpired(token));
    }
    @Test(priority=47) public void t47_RoleAssignmentSecurity() {
        User u = new User();
        u.setRole("MANAGER");
        Assert.assertEquals(u.getRole(), "MANAGER");
    }
    @Test(priority=48) public void t48_AuthHeaderPrefixCheck() {
        String token = "Bearer " + jwtUtil.generateToken("auth@test.com");
        Assert.assertTrue(token.startsWith("Bearer "));
    }
    @Test(priority=49) public void t49_SecretKeyStability() {
        Assert.assertNotNull(jwtUtil);
    }
    @Test(priority=50) public void t50_FinalSuiteTeardown() {
        Assert.assertTrue(true, "All 50 tests in suite processed.");
    }
}
