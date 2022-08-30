package br.com.batchenterprisemanagement.batch;

public class JobCompletionNotificationListener {

//    private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public void afterJob(JobExecution jobExecution) {
//        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
//            LOGGER.info("!!! JOB FINISHED! Time to verify the results");
//
//            String query = "SELECT brand, origin, characteristics FROM coffee";
//            jdbcTemplate.query(query, (rs, row) -> new Coffee(rs.getString(1), rs.getString(2), rs.getString(3)))
//                .forEach(coffee -> LOGGER.info("Found < {} > in the database.", coffee));
//        }
//    }

}
