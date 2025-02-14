package com.util;

import com.entity.*;
import com.exception.custom.NotFoundException;
import com.exception.custom.RoleException;
import com.exception.custom.UserException;
import com.repository.*;
import com.util.enums.AccountTypeEnum;
import com.util.enums.ApiMethodEnum;
import com.util.enums.GenderEnum;
import com.util.enums.RoleNameEnum;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BlogRepository blogRepository;
    private final HashtagRepository hashtagRepository;
    private final VideoRepository videoRepository;
    private final SubjectRepository subjectRepository;
    private final DocumentRepository documentRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final ExpertRepository expertRepository;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final Random random = new Random();
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("Database initialization started!");

//        createPermission();
        createRole();
        createUser();
        createExpert();
        createHashtag();
        createBlog();
        createVideo();
        createSubject();
        createDocument();
        createLesson();
        createQuestion();
        createQuiz();
        createCourse();

        logger.info("Database initialization completed!");
    }

    private void createPermission() {
        List<PermissionEntity> permissions = Arrays.asList(
                // auth controller
                new PermissionEntity("/auth/login/credentials", ApiMethodEnum.POST),
                new PermissionEntity("/auth/login/socials", ApiMethodEnum.POST),
                new PermissionEntity("/auth/refresh", ApiMethodEnum.GET),
                new PermissionEntity("/auth/logout", ApiMethodEnum.GET),

                // blog controller
                new PermissionEntity("/blogs", ApiMethodEnum.GET),
                new PermissionEntity("/blogs/pinned", ApiMethodEnum.GET),
                new PermissionEntity("/blogs/author", ApiMethodEnum.GET),
                new PermissionEntity("/blogs/{id}", ApiMethodEnum.GET),
                new PermissionEntity("/blogs/hashtag", ApiMethodEnum.GET)

                // tiep tuc di em
        );
        permissionRepository.saveAll(permissions);
    }

    private void createRole() {
        if (!roleRepository.existsBy()) {
            List<RoleEntity> roles = List.of(
                    new RoleEntity(RoleNameEnum.ADMIN, permissionRepository.findAll()),
                    new RoleEntity(RoleNameEnum.USER, null),
                    new RoleEntity(RoleNameEnum.EXPERT, null),
                    new RoleEntity(RoleNameEnum.MARKETING, null)
            );
            roleRepository.saveAll(roles);
        }
    }

    private void createUser() {
        if (!userRepository.existsBy()) {
            RoleEntity admin = roleRepository.findByRoleName(RoleNameEnum.ADMIN).orElseThrow(() -> new RoleException("Role name not existed!"));
            RoleEntity expert = roleRepository.findByRoleName(RoleNameEnum.EXPERT).orElseThrow(() -> new RoleException("Role name not existed!"));
            RoleEntity marketing = roleRepository.findByRoleName(RoleNameEnum.MARKETING).orElseThrow(() -> new RoleException("Role name not existed!"));
            RoleEntity user = roleRepository.findByRoleName(RoleNameEnum.USER).orElseThrow(() -> new RoleException("Role name not existed!"));

            List<UserEntity> users = new ArrayList<>(List.of(
                    UserEntity.builder()
                            .password(passwordEncoder.encode("123"))
                            .avatar("truc.jpg")
                            .fullname("Nguyen Vuong Truc Admin")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("trucnvhe180248@fpt.edu.vn")
                            .gender(GenderEnum.MALE)
                            .role(admin)
                            .build(),
                    UserEntity.builder()
                            .password(passwordEncoder.encode("123"))
                            .avatar("cuong.jpg")
                            .fullname("Do Xuan Cuong Admin")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("cuongdo13042004@gmail.com")
                            .gender(GenderEnum.MALE)
                            .role(admin)
                            .build(),
                    UserEntity.builder()
                            .password(passwordEncoder.encode("123"))
                            .avatar("dung.jpg")
                            .fullname("Tran Nam Dung Admin")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("dung06032004@gmail.com")
                            .gender(GenderEnum.MALE)
                            .role(admin)
                            .build(),
                    UserEntity.builder()
                            .password(passwordEncoder.encode("123"))
                            .avatar("duc.jpg")
                            .fullname("Nong Hoang Duc Admin")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("ducnhhe186325@fpt.edu.vn")
                            .gender(GenderEnum.MALE)
                            .role(admin)
                            .build(),
                    UserEntity.builder()
                            .password(passwordEncoder.encode("123"))
                            .avatar("truong.jpg")
                            .fullname("Luong Hoang Truong Admin")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("luongtruong15122004@gmail.com")
                            .gender(GenderEnum.MALE)
                            .role(admin)
                            .build(),
                    UserEntity.builder()
                            .password(passwordEncoder.encode("123"))
                            .avatar("truc.jpg")
                            .fullname("Nguyen Vuong Truc Expert")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("vuongtruc2004@gmail.com")
                            .gender(GenderEnum.MALE)
                            .role(expert)
                            .build(),
                    UserEntity.builder()
                            .password(passwordEncoder.encode("123"))
                            .avatar("truc.jpg")
                            .fullname("Vuong Truc Expert")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("vuongtruc20042@gmail.com")
                            .gender(GenderEnum.MALE)
                            .role(expert)
                            .build(),
                    UserEntity.builder()
                            .password(passwordEncoder.encode("123"))
                            .avatar("truc.jpg")
                            .fullname("Anh Truc Expert")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("vuongtruc20043@gmail.com")
                            .gender(GenderEnum.MALE)
                            .role(expert)
                            .build(),
                    UserEntity.builder()
                            .password(passwordEncoder.encode("123"))
                            .avatar("truc.jpg")
                            .fullname("Nguyen Vuong Truc Marketing")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("vuongtruc2008@gmail.com")
                            .gender(GenderEnum.MALE)
                            .role(marketing)
                            .build()
            ));
            for (int i = 1; i <= 10; i++) {
                users.add(
                        UserEntity.builder()
                                .password(passwordEncoder.encode("123"))
                                .fullname("Nguyen Vuong Truc " + i)
                                .accountType(AccountTypeEnum.CREDENTIALS)
                                .email(i + "@gmail.com")
                                .gender(i % 2 == 0 ? GenderEnum.MALE : GenderEnum.FEMALE)
                                .role(user)
                                .build()
                );
            }
            userRepository.saveAll(users);
        }
    }

    private void createExpert() {
        if (!expertRepository.existsBy()) {
            UserEntity user = userRepository.findByEmailAndAccountType("vuongtruc2004@gmail.com", AccountTypeEnum.CREDENTIALS)
                    .orElseThrow(() -> new UserException("Username not existed!"));
            ExpertEntity expert = ExpertEntity.builder()



                    .yearOfExperience(20)
                    .user(user)
                    .build();
            expertRepository.save(expert);

            UserEntity user1 = userRepository.findByEmailAndAccountType("vuongtruc20042@gmail.com", AccountTypeEnum.CREDENTIALS)
                    .orElseThrow(() -> new UserException("Username not existed!"));
            ExpertEntity expert1 = ExpertEntity.builder()


                    .yearOfExperience(10)
                    .user(user1)
                    .build();
            expertRepository.save(expert1);

            UserEntity user2 = userRepository.findByEmailAndAccountType("vuongtruc20043@gmail.com", AccountTypeEnum.CREDENTIALS)
                    .orElseThrow(() -> new UserException("Username not existed!"));
            ExpertEntity expert2 = ExpertEntity.builder()



                    .yearOfExperience(15)
                    .user(user2)
                    .build();
            expertRepository.save(expert2);
        }
    }

    private void createHashtag() {
        if (!hashtagRepository.existsBy()) {
            List<HashtagEntity> hashtags = List.of(
                    HashtagEntity.builder().tagName("java").build(),
                    HashtagEntity.builder().tagName("javascript").build(),
                    HashtagEntity.builder().tagName("typescript").build(),
                    HashtagEntity.builder().tagName("html").build(),
                    HashtagEntity.builder().tagName("css").build(),
                    HashtagEntity.builder().tagName("machine learning").build(),
                    HashtagEntity.builder().tagName("reactjs").build(),
                    HashtagEntity.builder().tagName("nextjs").build(),
                    HashtagEntity.builder().tagName("c++").build(),
                    HashtagEntity.builder().tagName("python").build(),
                    HashtagEntity.builder().tagName("spring boot").build(),
                    HashtagEntity.builder().tagName("docker").build(),
                    HashtagEntity.builder().tagName("kubernetes").build(),
                    HashtagEntity.builder().tagName("ai").build(),
                    HashtagEntity.builder().tagName("deep learning").build(),
                    HashtagEntity.builder().tagName("flutter").build(),
                    HashtagEntity.builder().tagName("nodejs").build(),
                    HashtagEntity.builder().tagName("golang").build(),
                    HashtagEntity.builder().tagName("rust").build(),
                    HashtagEntity.builder().tagName("database").build(),
                    HashtagEntity.builder().tagName("mysql").build(),
                    HashtagEntity.builder().tagName("mongodb").build(),
                    HashtagEntity.builder().tagName("postgresql").build(),
                    HashtagEntity.builder().tagName("graphql").build(),
                    HashtagEntity.builder().tagName("restapi").build(),
                    HashtagEntity.builder().tagName("android").build(),
                    HashtagEntity.builder().tagName("ios").build(),
                    HashtagEntity.builder().tagName("aws").build(),
                    HashtagEntity.builder().tagName("cloud computing").build(),
                    HashtagEntity.builder().tagName("cybersecurity").build()
            );
            hashtagRepository.saveAll(hashtags);
        }
    }

    private void createBlog() {
        if (!blogRepository.existsBy()) {
            UserEntity user = userRepository.findByEmailAndAccountType("trucnvhe180248@fpt.edu.vn", AccountTypeEnum.CREDENTIALS)
                    .orElseThrow(() -> new UserException("Username not existed!"));

            List<BlogEntity> blogs = List.of(
                    BlogEntity.builder()
                            .title("Lập Trình Web Từ A Đến Z: Hướng Dẫn Chi Tiết")
                            .content("<p>Bạn muốn học lập trình web nhưng không biết bắt đầu từ đâu? <strong>Bài viết này</strong> sẽ giúp bạn hiểu rõ các khái niệm quan trọng từ frontend đến backend.</p><p>Bạn sẽ học <em>HTML, CSS, JavaScript, React</em> và <strong>Spring Boot</strong> để xây dựng website chuyên nghiệp.</p>")
                            .plainContent(Jsoup.parse("<p>Bạn muốn học lập trình web nhưng không biết bắt đầu từ đâu? <strong>Bài viết này</strong> sẽ giúp bạn hiểu rõ các khái niệm quan trọng từ frontend đến backend.</p><p>Bạn sẽ học <em>HTML, CSS, JavaScript, React</em> và <strong>Spring Boot</strong> để xây dựng website chuyên nghiệp.</p>").text())
                            .user(user)
                            .thumbnail("1.jpg")
                            .hashtags(this.getRandomHashtags(3))
                            .pinned(true)
                            .build(),
                    BlogEntity.builder()
                            .title("Những Sai Lầm Cần Tránh Khi Học Lập Trình")
                            .content("<p>Khi mới học lập trình, nhiều người mắc phải những sai lầm phổ biến. <strong>Bài viết này</strong> sẽ chỉ ra những lỗi đó và cách khắc phục.</p><p>Tránh học lan man, tập trung vào thực hành và tham gia dự án thực tế để tiến bộ nhanh hơn.</p>")
                            .plainContent(Jsoup.parse("<p>Khi mới học lập trình, nhiều người mắc phải những sai lầm phổ biến. <strong>Bài viết này</strong> sẽ chỉ ra những lỗi đó và cách khắc phục.</p><p>Tránh học lan man, tập trung vào thực hành và tham gia dự án thực tế để tiến bộ nhanh hơn.</p>").text())
                            .user(user)
                            .thumbnail("2.jpg")
                            .hashtags(this.getRandomHashtags(5))
                            .published(false)
                            .build(),
                    BlogEntity.builder()
                            .title("Top 10 Công Cụ Hữu Ích Cho Lập Trình Viên")
                            .content("<p>Công cụ phù hợp giúp lập trình viên làm việc nhanh chóng và hiệu quả hơn. <strong>Dưới đây</strong> là danh sách 10 công cụ không thể thiếu.</p><ul><li>VS Code</li><li>Postman</li><li>Docker</li></ul>")
                            .plainContent(Jsoup.parse("<p>Công cụ phù hợp giúp lập trình viên làm việc nhanh chóng và hiệu quả hơn. <strong>Dưới đây</strong> là danh sách 10 công cụ không thể thiếu.</p><ul><li>VS Code</li><li>Postman</li><li>Docker</li></ul>").text())
                            .user(user)
                            .thumbnail("3.jpg")
                            .hashtags(this.getRandomHashtags(4))
                            .build(),
                    BlogEntity.builder()
                            .title("Làm Chủ Git Và GitHub: Hướng Dẫn Dành Cho Người Mới")
                            .content("<p><strong>Git và GitHub</strong> giúp lập trình viên quản lý mã nguồn và làm việc nhóm hiệu quả. Bài viết này sẽ hướng dẫn bạn từ cơ bản đến nâng cao.</p>")
                            .plainContent(Jsoup.parse("<p><strong>Git và GitHub</strong> giúp lập trình viên quản lý mã nguồn và làm việc nhóm hiệu quả. Bài viết này sẽ hướng dẫn bạn từ cơ bản đến nâng cao.</p>").text())
                            .user(user)
                            .thumbnail("4.jpg")
                            .hashtags(this.getRandomHashtags(6))
                            .build(),
                    BlogEntity.builder()
                            .title("Làm Sao Để Trở Thành Lập Trình Viên Full-Stack?")
                            .content("<p><strong>Full-stack developer</strong> là một trong những vị trí hot nhất. Bài viết này giúp bạn nắm vững kỹ năng cần thiết và lộ trình học tập hiệu quả.</p>")
                            .plainContent(Jsoup.parse("<p><strong>Full-stack developer</strong> là một trong những vị trí hot nhất. Bài viết này giúp bạn nắm vững kỹ năng cần thiết và lộ trình học tập hiệu quả.</p>").text())
                            .user(user)
                            .thumbnail("5.jpg")
                            .hashtags(this.getRandomHashtags(7))
                            .published(false)
                            .build(),
                    BlogEntity.builder()
                            .title("Cách Viết Code Sạch Và Dễ Bảo Trì")
                            .content("<p>Viết code không chỉ để máy tính hiểu, mà còn giúp đồng đội dễ đọc. Hãy áp dụng các nguyên tắc như <strong>DRY</strong> và <em>KISS</em> để tối ưu mã nguồn.</p>")
                            .plainContent(Jsoup.parse("<p>Viết code không chỉ để máy tính hiểu, mà còn giúp đồng đội dễ đọc. Hãy áp dụng các nguyên tắc như <strong>DRY</strong> và <em>KISS</em> để tối ưu mã nguồn.</p>").text())
                            .user(user)
                            .thumbnail("6.jpg")
                            .hashtags(this.getRandomHashtags(6))
                            .build(),
                    BlogEntity.builder()
                            .title("Học JavaScript Trong 30 Ngày: Lộ Trình Chi Tiết")
                            .content("<p>JavaScript là ngôn ngữ lập trình phổ biến nhất hiện nay. <strong>Bài viết này</strong> sẽ cung cấp cho bạn lộ trình học từ cơ bản đến nâng cao.</p>")
                            .plainContent(Jsoup.parse("<p>JavaScript là ngôn ngữ lập trình phổ biến nhất hiện nay. <strong>Bài viết này</strong> sẽ cung cấp cho bạn lộ trình học từ cơ bản đến nâng cao.</p>").text())
                            .user(user)
                            .thumbnail("7.jpg")
                            .hashtags(this.getRandomHashtags(5))
                            .build(),
                    BlogEntity.builder()
                            .title("Lập Trình Viên Có Cần Học Data Structures & Algorithms?")
                            .content("<p>Cấu trúc dữ liệu và thuật toán là một phần quan trọng trong lập trình. <strong>Bài viết này</strong> sẽ giúp bạn hiểu cách áp dụng chúng vào thực tế.</p>")
                            .plainContent(Jsoup.parse("<p>Cấu trúc dữ liệu và thuật toán là một phần quan trọng trong lập trình. <strong>Bài viết này</strong> sẽ giúp bạn hiểu cách áp dụng chúng vào thực tế.</p>").text())
                            .user(user)
                            .thumbnail("8.jpg")
                            .hashtags(this.getRandomHashtags(5))
                            .build(),
                    BlogEntity.builder()
                            .title("Những Ngôn Ngữ Lập Trình Phổ Biến Năm 2025")
                            .content("<p><strong>Python, JavaScript, Go</strong> và <em>Rust</em> là những ngôn ngữ có xu hướng phát triển mạnh trong năm tới.</p>")
                            .plainContent(Jsoup.parse("<p><strong>Python, JavaScript, Go</strong> và <em>Rust</em> là những ngôn ngữ có xu hướng phát triển mạnh trong năm tới.</p>").text())
                            .user(user)
                            .thumbnail("9.jpg")
                            .hashtags(this.getRandomHashtags(9))
                            .build(),
                    BlogEntity.builder()
                            .title("Phát Triển Ứng Dụng Mobile: Nên Chọn React Native Hay Flutter?")
                            .content("<p>Chọn <strong>React Native</strong> nếu bạn thích JavaScript, hoặc <strong>Flutter</strong> nếu bạn muốn hiệu suất cao với Dart.</p>")
                            .plainContent(Jsoup.parse("<p>Chọn <strong>React Native</strong> nếu bạn thích JavaScript, hoặc <strong>Flutter</strong> nếu bạn muốn hiệu suất cao với Dart.</p>").text())
                            .user(user)
                            .thumbnail("10.jpg")
                            .hashtags(this.getRandomHashtags(6))
                            .build(),
                    BlogEntity.builder()
                            .title("Bí Quyết Học Lập Trình Hiệu Quả Cho Người Mới Bắt Đầu")
                            .content("<p>Học lập trình cần kiên nhẫn và thực hành liên tục. <strong>Bạn nên</strong> đặt mục tiêu rõ ràng, làm bài tập thực tế và tham gia dự án nhóm.</p>")
                            .plainContent(Jsoup.parse("<p>Học lập trình cần kiên nhẫn và thực hành liên tục. <strong>Bạn nên</strong> đặt mục tiêu rõ ràng, làm bài tập thực tế và tham gia dự án nhóm.</p>").text())
                            .user(user)
                            .thumbnail("11.jpg")
                            .hashtags(this.getRandomHashtags(5))
                            .build(),
                    BlogEntity.builder()
                            .title("Tìm Hiểu Về DevOps Và CI/CD")
                            .content("<p><strong>DevOps</strong> giúp cải thiện quy trình phát triển phần mềm, kết hợp <strong>CI/CD</strong> để tự động hóa triển khai.</p>")
                            .plainContent(Jsoup.parse("<p><strong>DevOps</strong> giúp cải thiện quy trình phát triển phần mềm, kết hợp <strong>CI/CD</strong> để tự động hóa triển khai.</p>").text())
                            .user(user)
                            .thumbnail("12.jpg")
                            .hashtags(this.getRandomHashtags(4))
                            .build(),
                    BlogEntity.builder()
                            .title("Các Kỹ Năng Cần Có Để Trở Thành Lập Trình Viên Thành Công")
                            .content("<p>Để thành công trong lĩnh vực lập trình, bạn cần kỹ năng giải quyết vấn đề, giao tiếp và học hỏi liên tục.</p>")
                            .plainContent(Jsoup.parse("<p>Để thành công trong lĩnh vực lập trình, bạn cần kỹ năng giải quyết vấn đề, giao tiếp và học hỏi liên tục.</p>").text())
                            .user(user)
                            .thumbnail("13.jpg")
                            .hashtags(this.getRandomHashtags(6))
                            .published(false)
                            .build(),
                    BlogEntity.builder()
                            .title("Điều Quan Trọng Cần Biết Khi Làm Việc Với API")
                            .content("<p>Hiểu về <strong>RESTful API</strong> và <em>WebSocket</em> sẽ giúp bạn thiết kế các dịch vụ web hiệu quả hơn.</p>")
                            .plainContent(Jsoup.parse("<p>Hiểu về <strong>RESTful API</strong> và <em>WebSocket</em> sẽ giúp bạn thiết kế các dịch vụ web hiệu quả hơn.</p>").text())
                            .user(user)
                            .thumbnail("14.jpg")
                            .hashtags(this.getRandomHashtags(7))
                            .build(),
                    BlogEntity.builder()
                            .title("Phát Triển Phần Mềm Với Scrum: Lợi Ích Và Thực Hành")
                            .content("<p><strong>Scrum</strong> là phương pháp Agile phổ biến để phát triển phần mềm, giúp cải thiện năng suất và đảm bảo chất lượng.</p>")
                            .plainContent(Jsoup.parse("<p><strong>Scrum</strong> là phương pháp Agile phổ biến để phát triển phần mềm, giúp cải thiện năng suất và đảm bảo chất lượng.</p>").text())
                            .user(user)
                            .thumbnail("15.jpg")
                            .hashtags(this.getRandomHashtags(8))
                            .build()
            );
            blogRepository.saveAll(blogs);
        }
    }

    private void createVideo() {
        if (!videoRepository.existsBy()) {
            List<VideoEntity> videos = List.of(
                    VideoEntity.builder()
                            .title("When & Where to Add “use client” in React / Next.js (Client Components vs Server Components)")
                            .duration(603)
                            .videoUrl("https://youtu.be/Qdkg_mrniLk?si=OI-cJQU5nWWqzTls")
                            .build(),
                    VideoEntity.builder()
                            .title("10 common mistakes with the Next.js App Router")
                            .duration(1236)
                            .videoUrl("https://youtu.be/RBM03RihZVs?si=iwBzGi3UH-SnBBw-")
                            .build(),
                    VideoEntity.builder()
                            .title("What is Spring Framework?")
                            .duration(268)
                            .videoUrl("https://youtu.be/Zxwq3aW9ctU?si=_Q5PIIMS00zQKHWQ")
                            .build(),
                    VideoEntity.builder()
                            .title("Dependency Injection using Spring Boot")
                            .duration(813)
                            .videoUrl("https://youtu.be/9EoAXpjnsxM?si=YVGJNYXfQDF8_PB9")
                            .build(),
                    VideoEntity.builder()
                            .title("Autowire using Spring Boot")
                            .duration(915)
                            .videoUrl("https://youtu.be/ET39IFffr24?si=zsQixgt2XigHMWUP")
                            .build(),
                    VideoEntity.builder()
                            .title("TypeScript 5.8 Has 2 AWESOME Features")
                            .duration(393)
                            .videoUrl("https://youtu.be/vcVoyLQMCxU?si=Ved2PkGSMEEkpJ6I")
                            .build(),
                    VideoEntity.builder()
                            .title("Build anything with DeepSeek + Cline (CHEAP & EASY)")
                            .duration(420)
                            .videoUrl("https://youtu.be/ksr-_IXsVvs?si=gLpr3GDaAPYzZ6Ly")
                            .build(),
                    VideoEntity.builder()
                            .title("Tailwind v4 Is FINALLY Out – Here’s What’s New (and how to migrate!)")
                            .duration(567)
                            .videoUrl("https://youtu.be/ud913ekwAOQ?si=25uinR32Zx84cODw")
                            .build(),
                    VideoEntity.builder()
                            .title("My best CSS tips from 2024")
                            .duration(1014)
                            .videoUrl("https://youtu.be/lUU2OAAg4Uw?si=X2HhkieAKwfVFFQy")
                            .build(),
                    VideoEntity.builder()
                            .title("Next.js Server Actions (revalidatePath, useFormStatus & useOptimistic)")
                            .duration(1686)
                            .videoUrl("https://youtu.be/RadgkoJrhu0?si=KBRCSnMIjj_V8-xO")
                            .build(),
                    VideoEntity.builder()
                            .title("Difference Between Properties and Methods • C# Programming • C# Tutorial • Learn C#")
                            .duration(111)
                            .videoUrl("https://www.youtube.com/watch?v=eHGhtzu5p5s")
                            .build(),
                    VideoEntity.builder()
                            .title("Learn C# Sharp in Four Minutes")
                            .duration(249)
                            .videoUrl("https://www.youtube.com/watch?v=FqCHwSH56PA")
                            .build(),
                    VideoEntity.builder()
                            .title("What is .NET? What's C# and F#? What's the .NET Ecosystem? .NET Core Explained, what can .NET build?")
                            .duration(1133)
                            .videoUrl("https://www.youtube.com/watch?v=bEfBfBQq7EE")
                            .build(),
                    VideoEntity.builder()
                            .title("Learn C# Basics 3 of 3 with Scott and Cherita")
                            .duration(3552)
                            .videoUrl("https://www.youtube.com/watch?v=FqCHwSH56PA")
                            .build(),
                    VideoEntity.builder()
                            .title("Every single feature of C# in 10 minutes")
                            .duration(589)
                            .videoUrl("https://www.youtube.com/watch?v=J0FhV3dM80o")
                            .build(),
                    VideoEntity.builder()
                            .title("How C# and Java Are Actually Twins")
                            .duration(373)
                            .videoUrl("https://www.youtube.com/watch?v=gW6m36xR-2U")
                            .build(),
                    VideoEntity.builder()
                            .title("Upgrading Your Xamarin Project to MAUI")
                            .duration(244)
                            .videoUrl("https://www.youtube.com/watch?v=c7ValognY4I")
                            .build(),
                    VideoEntity.builder()
                            .title("Converting Java to C# using GPT-4")
                            .duration(518)
                            .videoUrl("https://www.youtube.com/watch?v=ivFv0PzCRPw")
                            .build(),
                    VideoEntity.builder()
                            .title("Why C# Has Multiple Ways to Write the Same Code")
                            .duration(590)
                            .videoUrl("https://www.youtube.com/watch?v=rjVAz6jLvUQ")
                            .build(),
                    VideoEntity.builder()
                            .title("Primary Constructors! New Feature Coming to C#")
                            .duration(211)
                            .videoUrl("https://www.youtube.com/watch?v=VarE4d2BqMo")
                            .build(),
                    VideoEntity.builder()
                            .title("Tổng quan về khóa học HTML CSS tại F8 | Học lập trình web cơ bản | Học được gì trong khóa học?")
                            .videoUrl("https://youtu.be/R6plN3FvzFY?si=GAioUucKIlup-h56")
                            .duration(194)
                            .build(),
                    VideoEntity.builder()
                            .title("HTML CSS là gì? | Ví dụ trực quan về HTML & CSS")
                            .videoUrl("https://youtu.be/zwsPND378OQ?si=lgry-wwVS8FV2Z7x")
                            .duration(148)
                            .build(),
                    VideoEntity.builder()
                            .title("Làm quen với Dev tools | Giới thiệu bộ công cụ Dev tools trên trình duyệt")
                            .videoUrl("https://youtu.be/7BJiPyN4zZ0?si=CbmHOInhnl4ABntt")
                            .duration(234)
                            .build(),
                    VideoEntity.builder()
                            .title("Comments trong HTML | Cú pháp mở và đóng Comments")
                            .videoUrl("https://youtu.be/JG0pdfdKjgQ?si=gwiv7dTx5ZShYMAe")
                            .duration(131)
                            .build(),
                    VideoEntity.builder()
                            .title("Attributes trong HTML | Thêm thuộc tính (Attributes) vào thẻ")
                            .videoUrl("https://youtu.be/UYpIh5pIkSA?si=c5VD8zUy-j9RdPQj")
                            .duration(114)
                            .build(),
                    VideoEntity.builder()
                            .title("CSS Variable là gì? | Cách đặt biến trong CSS")
                            .videoUrl("https://youtu.be/x9fnxVTkpP4?si=S90IsOYxRbcfAl_M")
                            .duration(228)
                            .build(),
                    VideoEntity.builder()
                            .title("CSS Background-clip | Thuộc tính Background-clip")
                            .videoUrl("https://youtu.be/hMWhvbCJIq8?si=qqD72d89KfUNSOpE")
                            .duration(254)
                            .build(),
                    VideoEntity.builder()
                            .title("Thuộc tính Background-size | CSS Background-size")
                            .videoUrl("https://youtu.be/8zsmGFNpqb4?si=XiO2JV9deVMaXVh4")
                            .duration(196)
                            .build(),
                    VideoEntity.builder()
                            .title("Thuộc tính Background-origin | CSS Background-origin")
                            .videoUrl("https://youtu.be/32a_fYd5zIo?si=PeEiGuBJrhPdLrNb")
                            .duration(177)
                            .build(),
                    VideoEntity.builder()
                            .title("Cú pháp shorthand | CSS Background shorthand")
                            .videoUrl("https://youtu.be/4hf8kMSRUJI?si=A2JZHoSjWENWxwf5")
                            .duration(149)
                            .build(),
                    VideoEntity.builder()
                            .title("Master TypeScript in an easy way")
                            .duration(609)
                            .videoUrl("https://www.youtube.com/watch?v=nFwmB1_iQ7A")
                            .build(),
                    VideoEntity.builder()
                            .title("Master React JS in easy way")
                            .duration(737)
                            .videoUrl("https://www.youtube.com/watch?v=E8lXC2mR6-k")
                            .build(),
                    VideoEntity.builder()
                            .title("Flutter Tutorial for Beginners #1 - Intro & Setup")
                            .duration(725)
                            .videoUrl("https://www.youtube.com/watch?v=1ukSR1GRtMU&list=PL4cUxeGkcC9jLYyp2Aoh6hcWuxFDX6PBJ")
                            .build(),
                    VideoEntity.builder()
                            .title("Angular Tutorial - 1 - Introduction")
                            .duration(375)
                            .videoUrl("https://www.youtube.com/watch?v=0eWrpsCLMJQ&list=PLC3y8-rFHvwhBRAgFinJR8KHIrCdTkZcZ")
                            .build(),
                    VideoEntity.builder()
                            .title("[100daysOfAngular] Day 1 - Introduction")
                            .duration(612)
                            .videoUrl("https://www.youtube.com/watch?v=NS6P1fpU77o&list=PLMTyi4Bfd5pW73uXw-6jgRxDwdPYqwk0r")
                            .build(),
                    VideoEntity.builder()
                            .title("Introduction to C++ Programming")
                            .duration(631)
                            .videoUrl("https://www.youtube.com/watch?v=s0g4ty29Xgg&list=PLBlnK6fEyqRh6isJ01MBnbNpV3ZsktSyS")
                            .build(),
                    VideoEntity.builder()
                            .title("Numbers, Integers, and Math [Pt 7] | C# for Beginners")
                            .duration(865)
                            .videoUrl("https://www.youtube.com/watch?v=ZXCMBOxry8A&list=PLdo4fOcmZ0oULFjxrOagaERVAMbmG20Xe&index=7")
                            .build(),
                    VideoEntity.builder()
                            .title("The Options Pattern in C# in 10 Minutes or Less")
                            .duration(638)
                            .videoUrl("https://www.youtube.com/watch?v=ko1Ie9gDydY")
                            .build(),
                    VideoEntity.builder()
                            .title("The Dictionary Data Structure in C# in 10 Minutes or Less")
                            .duration(619)
                            .videoUrl("https://www.youtube.com/watch?v=R94JHIXdTk0")
                            .build(),
                    VideoEntity.builder()
                            .title("JavaScript Basics in 10 Minutes")
                            .duration(708)
                            .videoUrl("https://www.youtube.com/watch?v=xwKbtUP87Dk")
                            .build(),
                    VideoEntity.builder()
                            .title("This CSS Property Replaces Hundreds of Lines of Code")
                            .duration(395)
                            .videoUrl("https://youtu.be/ElELqkwzcYM?si=km2dsDrSgfV7-02J")
                            .build(),
                    VideoEntity.builder()
                            .title("Learn CSS Positioning Quickly With A Real World Example")
                            .duration(511)
                            .videoUrl("https://youtu.be/MxEtxo_AaZ4?si=L0gTIT8ArVinwNf6")
                            .build(),
                    VideoEntity.builder()
                            .title("One Line Of Code Clip-Path By Master CSS")
                            .duration(310)
                            .videoUrl("https://youtu.be/g-R_YlDg2kQ?si=LKrzzvZWWUMqcmiG")
                            .build(),
                    VideoEntity.builder()
                            .title("Master React JS in easy way")
                            .duration(737)
                            .videoUrl("https://youtu.be/E8lXC2mR6-k?si=nO2BEjW4cF_iUSy7")
                            .build(),
                    VideoEntity.builder()
                            .title("Master React Hooks in easy way | useEffect")
                            .duration(505)
                            .videoUrl("https://youtu.be/g-R_YlDg2kQ?si=WD5uRfc851t9gJAY")
                            .build(),
                    VideoEntity.builder()
                            .title("NestJS in 100 Seconds")
                            .duration(140)
                            .videoUrl("https://youtu.be/0M8AYU_hPas?si=0_e2eFBhwQITGFer")
                            .build(),
                    VideoEntity.builder()
                            .title("Next.js 13 - The Basics")
                            .duration(539)
                            .videoUrl("https://youtu.be/mSgDEOyv8?si=T3VmDTg3PLsupT55")
                            .build(),
                    VideoEntity.builder()
                            .title("Express.js 5 is here (since a month already, actually)")
                            .duration(597)
                            .videoUrl("https://youtu.be/-MMjFX5UfN4?si=R851MIi8cRasOBkR")
                            .build(),
                    VideoEntity.builder()
                            .title("MongoDB in 100 Seconds")
                            .duration(146)
                            .videoUrl("https://youtu.be/-bt_y4Loofg?si=749xUTz-Yc5wc-Sv")
                            .build(),
                    VideoEntity.builder()
                            .title("MySQL vs MongoDB")
                            .duration(329)
                            .videoUrl("https://youtu.be/OdgZ0jr4jpM?si=POAUqP2o-dQFYLTD")
                            .build(),
                    VideoEntity.builder()
                            .title("Fetch vs. Axios in 1 minute")
                            .duration(84)
                            .videoUrl("https://youtu.be/OFWATycG_Wc?si=C7xIzojN9YoeY1RA")
                            .build(),
                    VideoEntity.builder()
                            .title("10 Advanced Tailwind Tricks Inspired by Shadcn")
                            .duration(590)
                            .videoUrl("https://youtu.be/9z2Ifq-OPEI?si=vJTfnI1VVYAmVsxJ")
                            .build(),
                    VideoEntity.builder()
                            .title("#8 Spring without Boot")
                            .duration(805)
                            .videoUrl("https://youtu.be/42X_fDrP-Vg?si=SUxHA2R6PXpPlE5a")
                            .build(),
                    VideoEntity.builder()
                            .title("#10 Constructor and Setter Injection in Spring")
                            .duration(946)
                            .videoUrl("https://youtu.be/02Mv2lc-h-8?si=9h-izl-x8qPD12G7")
                            .build(),
                    VideoEntity.builder()
                            .title("Mastering TypeScript Generics | Advanced TypeScript Concepts")
                            .duration(763)
                            .videoUrl("https://youtu.be/Ba3rJEOqbNA?si=A78LnUpbIsppRhJ7")
                            .build(),
                    VideoEntity.builder()
                            .title("Learn TypeScript Generics In 13 Minutes")
                            .duration(771)
                            .videoUrl("https://youtu.be/EcCTIExsqmI?si=Vjt-xdB4twm8hCx0")
                            .build(),
                    VideoEntity.builder()
                            .title("#2. Setup dự án thực hành | Tự Học Fullstack Next.js/Nest.js với TypeScript")
                            .duration(503)
                            .videoUrl("https://youtu.be/-aYoZhvn8-4?si=PYXk3TxFq1UjHqqA")
                            .build(),
                    VideoEntity.builder()
                            .title("NestJS MongoDB Connection: Quick Guide with 3 Approaches")
                            .duration(574)
                            .videoUrl("https://youtu.be/XXjfTQ7d-eo?si=-AY5N672c1Sm65RU")
                            .build(),
                    VideoEntity.builder()
                            .title("Framer Motion Tutorial: How to create Awesome text animation with framer-motion")
                            .duration(458)
                            .videoUrl("https://youtu.be/J8SFL3Z7zw4?si=YBuZkyjBx6rWZE-C")
                            .build(),
                    VideoEntity.builder()
                            .title("Framer Motion Tutorial: React Scroll Animations with Framer Motion")
                            .duration(312)
                            .videoUrl("https://youtu.be/bxzk0LEF5OE?si=1_VHKwuk9-K1aCC_")
                            .build()
            );
            videoRepository.saveAll(videos);
        }
    }

    private void createSubject() {
        if (!subjectRepository.existsBy()) {
            List<SubjectEntity> subjects = List.of(
                    SubjectEntity.builder().subjectName("Java").description("Ngôn ngữ lập trình phổ biến, chạy trên JVM.").thumbnail("java.png").build(),
                    SubjectEntity.builder().subjectName("Python").description("Ngôn ngữ lập trình dễ học, mạnh mẽ cho AI, Data Science.").thumbnail("python.png").build(),
                    SubjectEntity.builder().subjectName("JavaScript").description("Ngôn ngữ lập trình chính cho web frontend.").thumbnail("javascript.png").build(),
                    SubjectEntity.builder().subjectName("C++").description("Ngôn ngữ lập trình mạnh mẽ cho hệ thống và game.").thumbnail("cplus.png").build(),
                    SubjectEntity.builder().subjectName("C#").description("Ngôn ngữ phát triển ứng dụng trên nền tảng Microsoft.").thumbnail("csharp.png").build(),
                    SubjectEntity.builder().subjectName("React JS").description("Thư viện JavaScript phát triển UI động.").thumbnail("reactjs.png").build(),
                    SubjectEntity.builder().subjectName("Next JS").description("Framework React hỗ trợ SSR và SEO tốt.").thumbnail("nextjs.png").build(),
                    SubjectEntity.builder().subjectName("Spring Boot").description("Framework Java để phát triển ứng dụng web nhanh chóng.").thumbnail("springboot.png").build(),
                    SubjectEntity.builder().subjectName("PHP").description("Ngôn ngữ lập trình phổ biến cho backend web.").thumbnail("php.png").build(),
                    SubjectEntity.builder().subjectName("Ruby").description("Ngôn ngữ lập trình linh hoạt, thường dùng với Rails.").thumbnail("ruby.png").build(),
                    SubjectEntity.builder().subjectName("HTML").description("Ngôn ngữ đánh dấu để xây dựng trang web.").thumbnail("html.png").build(),
                    SubjectEntity.builder().subjectName("TypeScript").description("Phiên bản nâng cao của JavaScript với kiểu tĩnh.").thumbnail("typescript.png").build(),
                    SubjectEntity.builder().subjectName("CSS").description("Ngôn ngữ tạo kiểu cho trang web.").thumbnail("css.png").build(),
                    SubjectEntity.builder().subjectName("SASS").description("Tiền xử lý CSS giúp viết CSS dễ dàng hơn.").thumbnail("sass.png").build(),
                    SubjectEntity.builder().subjectName("Kotlin").description("Ngôn ngữ lập trình chính thức cho Android.").thumbnail("kotlin.png").build(),
                    SubjectEntity.builder().subjectName("Swift").description("Ngôn ngữ lập trình chính thức cho iOS.").thumbnail("swift.png").build(),
                    SubjectEntity.builder().subjectName("Nest JS").description("Framework Node.js để xây dựng backend hiệu quả.").thumbnail("nestjs.png").build(),
                    SubjectEntity.builder().subjectName("My SQL").description("Hệ quản trị cơ sở dữ liệu quan hệ phổ biến.").thumbnail("mysql.png").build(),
                    SubjectEntity.builder().subjectName("MongoDB").description("Cơ sở dữ liệu NoSQL dạng document.").thumbnail("mongodb.png").build(),
                    SubjectEntity.builder().subjectName("Vue JS").description("Framework JavaScript để xây dựng UI nhanh chóng.").thumbnail("vuejs.png").build(),
                    SubjectEntity.builder().subjectName("Tailwind CSS").description("Framework CSS tiện lợi, hỗ trợ thiết kế nhanh chóng.").thumbnail("tailwindcss.png").build(),
                    SubjectEntity.builder().subjectName("MUI").description("Thư viện UI cho React, dựa trên Material Design.").thumbnail("mui.png").build(),
                    SubjectEntity.builder().subjectName("Ant Design").description("Thư viện UI mạnh mẽ dành cho React.").thumbnail("antd.png").build(),
                    SubjectEntity.builder().subjectName("Bootstrap").description("Framework CSS phổ biến giúp phát triển web nhanh.").thumbnail("bootstrap.png").build(),
                    SubjectEntity.builder().subjectName("C").description("Ngôn ngữ lập trình mạnh mẽ, nền tảng cho nhiều ngôn ngữ khác.").thumbnail("c.png").build(),
                    SubjectEntity.builder().subjectName("Docker").description("Nền tảng container hóa giúp triển khai và quản lý ứng dụng dễ dàng hơn.").thumbnail("docker.png").build()
            );
            subjectRepository.saveAll(subjects);
        }
    }

    private void createDocument() {
        if (!documentRepository.existsBy()) {
            List<DocumentEntity> documents = List.of(
                    DocumentEntity.builder().title("Giới thiệu về Java").content("<h1>Java là gì?</h1><p>Java là một ngôn ngữ lập trình hướng đối tượng, mạnh mẽ.</p>").plainContent(Jsoup.parse("<h1>Java là gì?</h1><p>Java là một ngôn ngữ lập trình hướng đối tượng, mạnh mẽ.</p>").text()).build(),
                    DocumentEntity.builder().title("Tìm hiểu về Python").content("<h1>Python</h1><p>Python là một ngôn ngữ lập trình bậc cao, dễ học.</p>").plainContent(Jsoup.parse("<h1>Python</h1><p>Python là một ngôn ngữ lập trình bậc cao, dễ học.</p>").text()).build(),
                    DocumentEntity.builder().title("HTML và CSS là gì?").content("<h1>HTML & CSS</h1><p>HTML giúp tạo cấu trúc, còn CSS giúp tạo kiểu cho trang web.</p>").plainContent(Jsoup.parse("<h1>HTML & CSS</h1><p>HTML giúp tạo cấu trúc, còn CSS giúp tạo kiểu cho trang web.</p>").text()).build(),
                    DocumentEntity.builder().title("Giới thiệu về JavaScript").content("<h1>JavaScript</h1><p>Ngôn ngữ lập trình phổ biến trên web.</p>").plainContent(Jsoup.parse("<h1>JavaScript</h1><p>Ngôn ngữ lập trình phổ biến trên web.</p>").text()).build(),
                    DocumentEntity.builder().title("C++ là gì?").content("<h1>C++</h1><p>Một ngôn ngữ lập trình mạnh mẽ, dùng cho game, hệ thống nhúng.</p>").plainContent(Jsoup.parse("<h1>C++</h1><p>Một ngôn ngữ lập trình mạnh mẽ, dùng cho game, hệ thống nhúng.</p>").text()).build(),
                    DocumentEntity.builder().title("Lập trình với PHP").content("<h1>PHP</h1><p>PHP là một ngôn ngữ phổ biến để phát triển web.</p>").plainContent(Jsoup.parse("<h1>PHP</h1><p>PHP là một ngôn ngữ phổ biến để phát triển web.</p>").text()).build(),
                    DocumentEntity.builder().title("Tìm hiểu về Kotlin").content("<h1>Kotlin</h1><p>Kotlin là ngôn ngữ chính thức để phát triển Android.</p>").plainContent(Jsoup.parse("<h1>Kotlin</h1><p>Kotlin là ngôn ngữ chính thức để phát triển Android.</p>").text()).build(),
                    DocumentEntity.builder().title("Dart và Flutter").content("<h1>Dart & Flutter</h1><p>Dart là ngôn ngữ, Flutter là framework phát triển ứng dụng mobile.</p>").plainContent(Jsoup.parse("<h1>Dart & Flutter</h1><p>Dart là ngôn ngữ, Flutter là framework phát triển ứng dụng mobile.</p>").text()).build(),
                    DocumentEntity.builder().title("Go (Golang) là gì?").content("<h1>Golang</h1><p>Ngôn ngữ lập trình mạnh mẽ, hiệu suất cao do Google phát triển.</p>").plainContent(Jsoup.parse("<h1>Golang</h1><p>Ngôn ngữ lập trình mạnh mẽ, hiệu suất cao do Google phát triển.</p>").text()).build(),
                    DocumentEntity.builder().title("Rust và sự an toàn bộ nhớ").content("<h1>Rust</h1><p>Rust là một ngôn ngữ lập trình hệ thống với độ an toàn bộ nhớ cao.</p>").plainContent(Jsoup.parse("<h1>Rust</h1><p>Rust là một ngôn ngữ lập trình hệ thống với độ an toàn bộ nhớ cao.</p>").text()).build(),
                    DocumentEntity.builder().title("Swift và iOS Development").content("<h1>Swift</h1><p>Swift là ngôn ngữ chính thức để phát triển ứng dụng iOS.</p>").plainContent(Jsoup.parse("<h1>Swift</h1><p>Swift là ngôn ngữ chính thức để phát triển ứng dụng iOS.</p>").text()).build(),
                    DocumentEntity.builder().title("Scala và lập trình hàm").content("<h1>Scala</h1><p>Scala kết hợp lập trình hướng đối tượng và lập trình hàm.</p>").plainContent(Jsoup.parse("<h1>Scala</h1><p>Scala kết hợp lập trình hướng đối tượng và lập trình hàm.</p>").text()).build(),
                    DocumentEntity.builder().title("R và phân tích dữ liệu").content("<h1>R</h1><p>R là ngôn ngữ mạnh mẽ để phân tích dữ liệu và thống kê.</p>").plainContent(Jsoup.parse("<h1>R</h1><p>R là ngôn ngữ mạnh mẽ để phân tích dữ liệu và thống kê.</p>").text()).build(),
                    DocumentEntity.builder().title("TypeScript là gì?").content("<h1>TypeScript</h1><p>TypeScript là JavaScript có kiểu tĩnh, giúp phát triển ứng dụng lớn.</p>").plainContent(Jsoup.parse("<h1>TypeScript</h1><p>TypeScript là JavaScript có kiểu tĩnh, giúp phát triển ứng dụng lớn.</p>").text()).build(),
                    DocumentEntity.builder().title("Perl và lập trình hệ thống").content("<h1>Perl</h1><p>Perl được sử dụng nhiều trong quản trị hệ thống và xử lý văn bản.</p>").plainContent(Jsoup.parse("<h1>Perl</h1><p>Perl được sử dụng nhiều trong quản trị hệ thống và xử lý văn bản.</p>").text()).build(),
                    DocumentEntity.builder().title("Shell Script và Automation").content("<h1>Shell Script</h1><p>Shell Script giúp tự động hóa các tác vụ trên hệ điều hành Unix/Linux.</p>").plainContent(Jsoup.parse("<h1>Shell Script</h1><p>Shell Script giúp tự động hóa các tác vụ trên hệ điều hành Unix/Linux.</p>").text()).build(),
                    DocumentEntity.builder().title("Haskell và lập trình thuần hàm").content("<h1>Haskell</h1><p>Haskell là ngôn ngữ lập trình thuần hàm mạnh mẽ.</p>").plainContent(Jsoup.parse("<h1>Haskell</h1><p>Haskell là ngôn ngữ lập trình thuần hàm mạnh mẽ.</p>").text()).build(),
                    DocumentEntity.builder().title("Elixir và lập trình phân tán").content("<h1>Elixir</h1><p>Elixir là ngôn ngữ lập trình mạnh mẽ trong lập trình phân tán.</p>").plainContent(Jsoup.parse("<h1>Elixir</h1><p>Elixir là ngôn ngữ lập trình mạnh mẽ trong lập trình phân tán.</p>").text()).build(),
                    DocumentEntity.builder().title("Lua và lập trình nhúng").content("<h1>Lua</h1><p>Lua là ngôn ngữ nhẹ, mạnh mẽ cho lập trình nhúng.</p>").plainContent(Jsoup.parse("<h1>Lua</h1><p>Lua là ngôn ngữ nhẹ, mạnh mẽ cho lập trình nhúng.</p>").text()).build(),
                    DocumentEntity.builder().title("Lập trình hướng đối tượng").content("<h1>OOP là gì?</h1><p>OOP (Object-Oriented Programming) là một mô hình lập trình dựa trên đối tượng.</p>").plainContent(Jsoup.parse("<h1>OOP là gì?</h1><p>OOP (Object-Oriented Programming) là một mô hình lập trình dựa trên đối tượng.</p>").text()).build(),
                    DocumentEntity.builder().title("Lập trình Functional").content("<h1>Functional Programming</h1><p>Lập trình hàm là một mô hình lập trình dựa trên các hàm toán học.</p>").plainContent(Jsoup.parse("<h1>Functional Programming</h1><p>Lập trình hàm là một mô hình lập trình dựa trên các hàm toán học.</p>").text()).build(),
                    DocumentEntity.builder().title("JavaScript cơ bản").content("<h1>JavaScript là gì?</h1><p>JavaScript là một ngôn ngữ lập trình phổ biến trên web.</p>").plainContent(Jsoup.parse("<h1>JavaScript là gì?</h1><p>JavaScript là một ngôn ngữ lập trình phổ biến trên web.</p>").text()).build(),
                    DocumentEntity.builder().title("Python và ứng dụng").content("<h1>Python là gì?</h1><p>Python là một ngôn ngữ lập trình bậc cao, dễ học và mạnh mẽ.</p>").plainContent(Jsoup.parse("<h1>Python là gì?</h1><p>Python là một ngôn ngữ lập trình bậc cao, dễ học và mạnh mẽ.</p>").text()).build(),
                    DocumentEntity.builder().title("C++ và lập trình hệ thống").content("<h1>C++ là gì?</h1><p>C++ là một ngôn ngữ lập trình mạnh mẽ, thường được sử dụng trong lập trình hệ thống.</p>").plainContent(Jsoup.parse("<h1>C++ là gì?</h1><p>C++ là một ngôn ngữ lập trình mạnh mẽ, thường được sử dụng trong lập trình hệ thống.</p>").text()).build(),
                    DocumentEntity.builder().title("Spring Boot cơ bản").content("<h1>Spring Boot là gì?</h1><p>Spring Boot là một framework giúp phát triển ứng dụng Java dễ dàng hơn.</p>").plainContent(Jsoup.parse("<h1>Spring Boot là gì?</h1><p>Spring Boot là một framework giúp phát triển ứng dụng Java dễ dàng hơn.</p>").text()).build(),
                    DocumentEntity.builder().title("REST API là gì?").content("<h1>REST API</h1><p>REST API là một tiêu chuẩn phổ biến để xây dựng các dịch vụ web.</p>").plainContent(Jsoup.parse("<h1>REST API</h1><p>REST API là một tiêu chuẩn phổ biến để xây dựng các dịch vụ web.</p>").text()).build(),
                    DocumentEntity.builder().title("SQL và cơ sở dữ liệu").content("<h1>SQL là gì?</h1><p>SQL là ngôn ngữ truy vấn dữ liệu phổ biến cho hệ quản trị cơ sở dữ liệu.</p>").plainContent(Jsoup.parse("<h1>SQL là gì?</h1><p>SQL là ngôn ngữ truy vấn dữ liệu phổ biến cho hệ quản trị cơ sở dữ liệu.</p>").text()).build(),
                    DocumentEntity.builder().title("Docker và DevOps").content("<h1>Docker là gì?</h1><p>Docker là một nền tảng giúp đóng gói và triển khai ứng dụng dễ dàng.</p>").plainContent(Jsoup.parse("<h1>Docker là gì?</h1><p>Docker là một nền tảng giúp đóng gói và triển khai ứng dụng dễ dàng.</p>").text()).build(),
                    DocumentEntity.builder().title("Machine Learning với Python").content("<h1>Machine Learning</h1><p>Machine Learning là một lĩnh vực của trí tuệ nhân tạo giúp máy tính học từ dữ liệu.</p>").plainContent(Jsoup.parse("<h1>Machine Learning</h1><p>Machine Learning là một lĩnh vực của trí tuệ nhân tạo giúp máy tính học từ dữ liệu.</p>").text()).build(),
                    DocumentEntity.builder().title("Lập trình ReactJS").content("<h1>ReactJS là gì?</h1><p>ReactJS là một thư viện JavaScript phổ biến để xây dựng giao diện người dùng.</p>").plainContent(Jsoup.parse("<h1>ReactJS là gì?</h1><p>ReactJS là một thư viện JavaScript phổ biến để xây dựng giao diện người dùng.</p>").text()).build(),
                    DocumentEntity.builder().title("Lập trình Node.js").content("<h1>Node.js là gì?</h1><p>Node.js là một runtime JavaScript giúp chạy JavaScript phía server.</p>").plainContent(Jsoup.parse("<h1>Node.js là gì?</h1><p>Node.js là một runtime JavaScript giúp chạy JavaScript phía server.</p>").text()).build(),
                    DocumentEntity.builder().title("NoSQL và MongoDB").content("<h1>NoSQL là gì?</h1><p>NoSQL là một mô hình cơ sở dữ liệu linh hoạt, phổ biến với MongoDB.</p>").plainContent(Jsoup.parse("<h1>NoSQL là gì?</h1><p>NoSQL là một mô hình cơ sở dữ liệu linh hoạt, phổ biến với MongoDB.</p>").text()).build(),
                    DocumentEntity.builder().title("Tìm hiểu về TypeScript").content("<h1>TypeScript là gì?</h1><p>TypeScript là một phần mở rộng của JavaScript giúp code an toàn hơn.</p>").plainContent(Jsoup.parse("<h1>TypeScript là gì?</h1><p>TypeScript là một phần mở rộng của JavaScript giúp code an toàn hơn.</p>").text()).build(),
                    DocumentEntity.builder().title("Viết API với Express.js").content("<h1>Express.js</h1><p>Express.js là một framework giúp xây dựng ứng dụng web với Node.js dễ dàng hơn.</p>").plainContent(Jsoup.parse("<h1>Express.js</h1><p>Express.js là một framework giúp xây dựng ứng dụng web với Node.js dễ dàng hơn.</p>").text()).build(),
                    DocumentEntity.builder().title("Kubernetes cơ bản").content("<h1>Kubernetes là gì?</h1><p>Kubernetes là một hệ thống quản lý container phổ biến.</p>").plainContent(Jsoup.parse("<h1>Kubernetes là gì?</h1><p>Kubernetes là một hệ thống quản lý container phổ biến.</p>").text()).build(),
                    DocumentEntity.builder().title("GraphQL và REST API").content("<h1>GraphQL là gì?</h1><p>GraphQL là một công nghệ thay thế REST API, giúp truy vấn dữ liệu linh hoạt hơn.</p>").plainContent(Jsoup.parse("<h1>GraphQL là gì?</h1><p>GraphQL là một công nghệ thay thế REST API, giúp truy vấn dữ liệu linh hoạt hơn.</p>").text()).build(),
                    DocumentEntity.builder().title("Lập trình Android với Kotlin").content("<h1>Kotlin cho Android</h1><p>Kotlin là ngôn ngữ chính thức để lập trình ứng dụng Android.</p>").plainContent(Jsoup.parse("<h1>Kotlin cho Android</h1><p>Kotlin là ngôn ngữ chính thức để lập trình ứng dụng Android.</p>").text()).build(),
                    DocumentEntity.builder().title("Lập trình iOS với Swift").content("<h1>Swift là gì?</h1><p>Swift là ngôn ngữ lập trình chính thức của Apple dành cho iOS và macOS.</p>").plainContent(Jsoup.parse("<h1>Swift là gì?</h1><p>Swift là ngôn ngữ lập trình chính thức của Apple dành cho iOS và macOS.</p>").text()).build(),
                    DocumentEntity.builder().title("Tìm hiểu về CI/CD").content("<h1>CI/CD</h1><p>CI/CD là quy trình tự động hóa trong phát triển phần mềm, giúp cải thiện tốc độ và chất lượng triển khai.</p>").plainContent(Jsoup.parse("<h1>CI/CD</h1><p>CI/CD là quy trình tự động hóa trong phát triển phần mềm, giúp cải thiện tốc độ và chất lượng triển khai.</p>").text()).build(),
                    DocumentEntity.builder().title("Tìm hiểu về DSA").content("<h1>DSA là gì?</h1><p>DSA (Data Structures and Algorithms) là nền tảng quan trọng trong lập trình.</p>").plainContent(Jsoup.parse("<h1>DSA là gì?</h1><p>DSA (Data Structures and Algorithms) là nền tảng quan trọng trong lập trình.</p>").text()).build(),
                    DocumentEntity.builder().title("Tối ưu mã nguồn với Clean Code").content("<h1>Clean Code</h1><p>Clean Code giúp mã nguồn dễ đọc, bảo trì và mở rộng hơn.</p>").plainContent(Jsoup.parse("<h1>Clean Code</h1><p>Clean Code giúp mã nguồn dễ đọc, bảo trì và mở rộng hơn.</p>").text()).build(),
                    DocumentEntity.builder().title("Thiết kế phần mềm với Design Patterns").content("<h1>Design Patterns</h1><p>Design Patterns giúp giải quyết các vấn đề lập trình một cách tối ưu.</p>").plainContent(Jsoup.parse("<h1>Design Patterns</h1><p>Design Patterns giúp giải quyết các vấn đề lập trình một cách tối ưu.</p>").text()).build(),
                    DocumentEntity.builder().title("Lập trình Asynchronous trong JavaScript").content("<h1>Async JavaScript</h1><p>Async/Await giúp viết mã JavaScript bất đồng bộ dễ hiểu hơn.</p>").plainContent(Jsoup.parse("<h1>Async JavaScript</h1><p>Async/Await giúp viết mã JavaScript bất đồng bộ dễ hiểu hơn.</p>").text()).build(),
                    DocumentEntity.builder().title("Spring Security và Authentication").content("<h1>Spring Security</h1><p>Spring Security giúp bảo mật ứng dụng Java bằng xác thực và phân quyền.</p>").plainContent(Jsoup.parse("<h1>Spring Security</h1><p>Spring Security giúp bảo mật ứng dụng Java bằng xác thực và phân quyền.</p>").text()).build(),
                    DocumentEntity.builder().title("OAuth 2.0 và bảo mật API").content("<h1>OAuth 2.0</h1><p>OAuth 2.0 là giao thức giúp xác thực người dùng an toàn cho API.</p>").plainContent(Jsoup.parse("<h1>OAuth 2.0</h1><p>OAuth 2.0 là giao thức giúp xác thực người dùng an toàn cho API.</p>").text()).build(),
                    DocumentEntity.builder().title("Microservices với Spring Boot").content("<h1>Microservices</h1><p>Microservices là kiến trúc phần mềm giúp phát triển hệ thống linh hoạt.</p>").plainContent(Jsoup.parse("<h1>Microservices</h1><p>Microservices là kiến trúc phần mềm giúp phát triển hệ thống linh hoạt.</p>").text()).build(),
                    DocumentEntity.builder().title("Message Queue với RabbitMQ").content("<h1>RabbitMQ</h1><p>RabbitMQ là hệ thống hàng đợi tin nhắn giúp giao tiếp giữa các dịch vụ.</p>").plainContent(Jsoup.parse("<h1>RabbitMQ</h1><p>RabbitMQ là hệ thống hàng đợi tin nhắn giúp giao tiếp giữa các dịch vụ.</p>").text()).build(),
                    DocumentEntity.builder().title("Caching với Redis").content("<h1>Redis là gì?</h1><p>Redis là một cơ sở dữ liệu NoSQL in-memory giúp tăng tốc truy vấn.</p>").plainContent(Jsoup.parse("<h1>Redis là gì?</h1><p>Redis là một cơ sở dữ liệu NoSQL in-memory giúp tăng tốc truy vấn.</p>").text()).build(),
                    DocumentEntity.builder().title("Tìm hiểu về WebSockets").content("<h1>WebSockets</h1><p>WebSockets giúp giao tiếp thời gian thực giữa client và server.</p>").plainContent(Jsoup.parse("<h1>WebSockets</h1><p>WebSockets giúp giao tiếp thời gian thực giữa client và server.</p>").text()).build(),
                    DocumentEntity.builder().title("Machine Learning với TensorFlow").content("<h1>TensorFlow</h1><p>TensorFlow là một thư viện học máy phổ biến của Google.</p>").plainContent(Jsoup.parse("<h1>TensorFlow</h1><p>TensorFlow là một thư viện học máy phổ biến của Google.</p>").text()).build(),
                    DocumentEntity.builder().title("Computer Vision với OpenCV").content("<h1>OpenCV</h1><p>OpenCV là một thư viện giúp xử lý ảnh và video.</p>").plainContent(Jsoup.parse("<h1>OpenCV</h1><p>OpenCV là một thư viện giúp xử lý ảnh và video.</p>").text()).build(),
                    DocumentEntity.builder().title("Blockchain và Smart Contracts").content("<h1>Blockchain</h1><p>Blockchain là công nghệ giúp lưu trữ dữ liệu phân tán an toàn.</p>").plainContent(Jsoup.parse("<h1>Blockchain</h1><p>Blockchain là công nghệ giúp lưu trữ dữ liệu phân tán an toàn.</p>").text()).build(),
                    DocumentEntity.builder().title("Tìm hiểu về Web3").content("<h1>Web3</h1><p>Web3 giúp xây dựng các ứng dụng phi tập trung trên blockchain.</p>").plainContent(Jsoup.parse("<h1>Web3</h1><p>Web3 giúp xây dựng các ứng dụng phi tập trung trên blockchain.</p>").text()).build(),
                    DocumentEntity.builder().title("Big Data với Apache Spark").content("<h1>Apache Spark</h1><p>Apache Spark là framework xử lý dữ liệu lớn mạnh mẽ.</p>").plainContent(Jsoup.parse("<h1>Apache Spark</h1><p>Apache Spark là framework xử lý dữ liệu lớn mạnh mẽ.</p>").text()).build(),
                    DocumentEntity.builder().title("Ứng dụng AI trong lập trình").content("<h1>AI và lập trình</h1><p>AI giúp lập trình viên tạo ra các hệ thống thông minh hơn.</p>").plainContent(Jsoup.parse("<h1>AI và lập trình</h1><p>AI giúp lập trình viên tạo ra các hệ thống thông minh hơn.</p>").text()).build(),
                    DocumentEntity.builder().title("Unit Testing với JUnit").content("<h1>JUnit</h1><p>JUnit là một framework hỗ trợ kiểm thử đơn vị trong Java.</p>").plainContent(Jsoup.parse("<h1>JUnit</h1><p>JUnit là một framework hỗ trợ kiểm thử đơn vị trong Java.</p>").text()).build(),
                    DocumentEntity.builder().title("Tìm hiểu về Test-Driven Development").content("<h1>TDD là gì?</h1><p>TDD là phương pháp phát triển phần mềm dựa trên kiểm thử.</p>").plainContent(Jsoup.parse("<h1>TDD là gì?</h1><p>TDD là phương pháp phát triển phần mềm dựa trên kiểm thử.</p>").text()).build(),
                    DocumentEntity.builder().title("Refactoring code là gì?").content("<h1>Refactoring</h1><p>Refactoring giúp cải thiện chất lượng mã nguồn mà không thay đổi chức năng.</p>").plainContent(Jsoup.parse("<h1>Refactoring</h1><p>Refactoring giúp cải thiện chất lượng mã nguồn mà không thay đổi chức năng.</p>").text()).build(),
                    DocumentEntity.builder().title("Học lập trình hiệu quả").content("<h1>Làm sao để học lập trình tốt?</h1><p>Học lập trình cần kết hợp thực hành và lý thuyết.</p>").plainContent(Jsoup.parse("<h1>Làm sao để học lập trình tốt?</h1><p>Học lập trình cần kết hợp thực hành và lý thuyết.</p>").text()).build(),
                    DocumentEntity.builder().title("Tìm hiểu về Docker").content("<h1>Docker là gì?</h1><p>Docker giúp đóng gói và triển khai ứng dụng dễ dàng với container.</p>").plainContent(Jsoup.parse("<h1>Docker là gì?</h1><p>Docker giúp đóng gói và triển khai ứng dụng dễ dàng với container.</p>").text()).build()
            );
            documentRepository.saveAll(documents);
        }
    }

    private void createQuestion() {
        if (!questionRepository.existsBy()) {
            List<QuestionEntity> questions = List.of(
                    new QuestionEntity("Ngôn ngữ lập trình nào được sử dụng để phát triển ứng dụng Android?"),
                    new QuestionEntity("HTML là viết tắt của cụm từ nào?"),
                    new QuestionEntity("Trong Python, hàm nào được sử dụng để in ra màn hình?"),
                    new QuestionEntity("CSS được sử dụng để làm gì trong phát triển web?"),
                    new QuestionEntity("JavaScript là ngôn ngữ lập trình loại gì?"),
                    new QuestionEntity("Git là gì trong phát triển phần mềm?"),
                    new QuestionEntity("Trong Java, từ khóa 'final' có ý nghĩa gì?"),
                    new QuestionEntity("API là viết tắt của cụm từ nào?"),
                    new QuestionEntity("Trong cơ sở dữ liệu, SQL là viết tắt của cụm từ nào?"),
                    new QuestionEntity("Framework nào được sử dụng để phát triển ứng dụng web bằng Python?"),
                    new QuestionEntity("Trong lập trình hướng đối tượng, tính đa hình (polymorphism) là gì?"),
                    new QuestionEntity("Trong JavaScript, 'NaN' có nghĩa là gì?"),
                    new QuestionEntity("Trong C++, từ khóa 'virtual' được sử dụng để làm gì?"),
                    new QuestionEntity("Trong phát triển web, REST là gì?"),
                    new QuestionEntity("Trong Python, thư viện nào được sử dụng để phân tích dữ liệu?"),
                    new QuestionEntity("Trong Java, 'JVM' là viết tắt của cụm từ nào?"),
                    new QuestionEntity("Trong lập trình, 'debugging' là gì?"),
                    new QuestionEntity("Trong CSS, 'flexbox' được sử dụng để làm gì?"),
                    new QuestionEntity("Trong JavaScript, 'closure' là gì?"),
                    new QuestionEntity("Trong phát triển ứng dụng di động, 'Flutter' là gì?")
            );
            for (QuestionEntity question : questions) {
                boolean isCorrect = random.nextBoolean();
                Set<AnswerEntity> set = new HashSet<>(List.of(
                        new AnswerEntity("Câu trả lời A", isCorrect, question),
                        new AnswerEntity("Câu trả lời B", isCorrect, question),
                        new AnswerEntity("Câu trả lời C", isCorrect, question),
                        new AnswerEntity("Câu trả lời D", isCorrect, question),
                        new AnswerEntity("Câu trả lời E", isCorrect, question),
                        new AnswerEntity("Câu trả lời F", isCorrect, question)
                ));
                question.setAnswers(set);
            }
            questionRepository.saveAll(questions);
        }
    }

    private void createLesson() {
        if (!lessonRepository.existsBy()) {
            List<LessonEntity> lessons = List.of(
                    LessonEntity.builder().title("Giới thiệu về Java").description("Tìm hiểu tổng quan về Java và ứng dụng thực tế.").build(),
                    LessonEntity.builder().title("Lập trình hướng đối tượng").description("Các khái niệm OOP trong Java: class, object, inheritance.").build(),
                    LessonEntity.builder().title("Cấu trúc dữ liệu và giải thuật").description("Các thuật toán sắp xếp, tìm kiếm, danh sách liên kết.").build(),
                    LessonEntity.builder().title("Làm việc với MySQL").description("Cách sử dụng MySQL, thiết kế database.").build(),
                    LessonEntity.builder().title("Spring Boot cơ bản").description("Tìm hiểu về Spring Boot và cách tạo ứng dụng.").build(),
                    LessonEntity.builder().title("RESTful API với Spring Boot").description("Học cách tạo API trong Spring Boot.").build(),
                    LessonEntity.builder().title("Bảo mật Spring Boot").description("JWT, OAuth2 và bảo mật trong ứng dụng.").build(),
                    LessonEntity.builder().title("Làm việc với Hibernate").description("ORM trong Java với Hibernate.").build(),
                    LessonEntity.builder().title("Lập trình Web với React").description("Giới thiệu về React và cách xây dựng ứng dụng web.").build(),
                    LessonEntity.builder().title("Kết nối Frontend và Backend").description("Cách tích hợp React với API từ Spring Boot.").build(),
                    LessonEntity.builder().title("Redux trong React").description("Quản lý trạng thái ứng dụng với Redux.").build(),
                    LessonEntity.builder().title("Next.js và SSR").description("Tìm hiểu về Next.js và Server-side Rendering.").build(),
                    LessonEntity.builder().title("CI/CD với GitHub Actions").description("Tự động hóa quy trình triển khai ứng dụng.").build(),
                    LessonEntity.builder().title("Docker và Kubernetes").description("Triển khai ứng dụng với Docker và Kubernetes.").build(),
                    LessonEntity.builder().title("Microservices với Spring Cloud").description("Xây dựng hệ thống Microservices với Spring Cloud.").build(),
                    LessonEntity.builder().title("GraphQL trong Java").description("Tìm hiểu về GraphQL và cách sử dụng với Spring Boot.").build(),
                    LessonEntity.builder().title("Lập trình Android với Kotlin").description("Xây dựng ứng dụng Android hiện đại với Kotlin.").build(),
                    LessonEntity.builder().title("Machine Learning với Python").description("Cơ bản về Machine Learning, AI, Data Science.").build(),
                    LessonEntity.builder().title("Advanced Java Programming").description("Nâng cao kỹ năng Java với multithreading, stream API.").build(),
                    LessonEntity.builder().title("TDD với JUnit").description("Lập trình hướng kiểm thử với JUnit và Mockito.").build(),
                    LessonEntity.builder().title("Bộ nhớ đệm với Redis").description("Tăng tốc hiệu suất ứng dụng với Redis Cache.").build(),
                    LessonEntity.builder().title("Kafka và Event-Driven Architecture").description("Xây dựng hệ thống phân tán với Apache Kafka.").build(),
                    LessonEntity.builder().title("Xây dựng API GraphQL").description("Tạo GraphQL API với Spring Boot.").build(),
                    LessonEntity.builder().title("OAuth2 và OpenID Connect").description("Tích hợp OAuth2 và OpenID Connect trong Spring Security.").build(),
                    LessonEntity.builder().title("WebSockets với Spring Boot").description("Xây dựng ứng dụng real-time với WebSockets.").build(),
                    LessonEntity.builder().title("Elasticsearch cho Big Data").description("Tìm kiếm nhanh với Elasticsearch và Kibana.").build(),
                    LessonEntity.builder().title("Golang cho Backend Developers").description("Học cách sử dụng Golang để xây dựng API mạnh mẽ.").build(),
                    LessonEntity.builder().title("TensorFlow với Java").description("Xây dựng mô hình Machine Learning với TensorFlow và Java.").build(),
                    LessonEntity.builder().title("Xây dựng hệ thống CI/CD nâng cao").description("CI/CD chuyên sâu với Kubernetes và ArgoCD.").build(),
                    LessonEntity.builder().title("Xây dựng ứng dụng Serverless").description("Sử dụng AWS Lambda để phát triển ứng dụng không máy chủ.").build(),
                    LessonEntity.builder().title("Biến và kiểu dữ liệu trong Java").description("Tìm hiểu về biến, kiểu dữ liệu cơ bản và cách sử dụng chúng.").build(),
                    LessonEntity.builder().title("Cấu trúc điều kiện và vòng lặp").description("Hướng dẫn sử dụng if-else, switch-case và vòng lặp trong Java.").build(),
                    LessonEntity.builder().title("Lập trình hướng đối tượng với Java").description("Giới thiệu về lập trình OOP, các khái niệm class, object, inheritance, polymorphism.").build(),
                    LessonEntity.builder().title("Làm việc với Array và Collection").description("Học cách sử dụng mảng, ArrayList, HashMap trong Java.").build(),
                    LessonEntity.builder().title("Xử lý ngoại lệ trong Java").description("Hướng dẫn cách sử dụng try-catch-finally để xử lý lỗi chương trình.").build(),
                    LessonEntity.builder().title("Làm việc với File trong Java").description("Đọc ghi file bằng FileReader, FileWriter, BufferedReader và BufferedWriter.").build(),
                    LessonEntity.builder().title("Giới thiệu về JDBC").description("Kết nối Java với MySQL sử dụng JDBC API.").build(),
                    LessonEntity.builder().title("Lập trình đa luồng trong Java").description("Học cách tạo và quản lý thread trong Java.").build(),
                    LessonEntity.builder().title("Xây dựng ứng dụng Java Swing").description("Hướng dẫn tạo giao diện đồ họa với Java Swing.").build(),
                    LessonEntity.builder().title("Làm việc với API trong Java").description("Gọi API RESTful và xử lý dữ liệu JSON bằng Java.").build(),
                    LessonEntity.builder().title("Spring Boot: Giới thiệu và Cấu hình").description("Cài đặt và cấu hình dự án Spring Boot đầu tiên.").build(),
                    LessonEntity.builder().title("Spring Boot: Xây dựng API RESTful").description("Học cách xây dựng API RESTful với Spring Boot.").build(),
                    LessonEntity.builder().title("Spring Boot: Làm việc với JPA và MySQL").description("Hướng dẫn tích hợp JPA với MySQL trong Spring Boot.").build(),
                    LessonEntity.builder().title("Spring Security: Xác thực và phân quyền").description("Hướng dẫn triển khai xác thực và phân quyền trong Spring Security.").build(),
                    LessonEntity.builder().title("Dự án thực tế: Xây dựng website bán hàng với Spring Boot").description("Ứng dụng kiến thức đã học vào dự án thực tế.").build(),
                    LessonEntity.builder().title("Giới thiệu về Python").description("Tổng quan về Python, cài đặt và thiết lập môi trường.").build(),
                    LessonEntity.builder().title("Biến và kiểu dữ liệu trong Python").description("Học về biến, kiểu dữ liệu và cách sử dụng trong Python.").build(),
                    LessonEntity.builder().title("Cấu trúc điều kiện và vòng lặp").description("Sử dụng if-else, for, while trong Python.").build(),
                    LessonEntity.builder().title("Lập trình hướng đối tượng trong Python").description("Class, object, inheritance, polymorphism trong Python.").build(),
                    LessonEntity.builder().title("Làm việc với List, Tuple, Dictionary").description("Học cách thao tác với các cấu trúc dữ liệu trong Python.").build(),
                    LessonEntity.builder().title("Xử lý file trong Python").description("Đọc, ghi file với Python sử dụng open, read, write.").build(),
                    LessonEntity.builder().title("Lập trình bất đồng bộ trong Python").description("Async, await trong Python để tối ưu hiệu suất.").build(),
                    LessonEntity.builder().title("Dự án Python: Xây dựng ứng dụng quản lý công việc").description("Ứng dụng thực tế giúp bạn hiểu sâu hơn về lập trình Python.").build(),
                    LessonEntity.builder().title("Giới thiệu về React.js").description("Học React từ cơ bản, hiểu về JSX và component.").build(),
                    LessonEntity.builder().title("React Hooks và State Management").description("Sử dụng useState, useEffect, Context API trong React.").build(),
                    LessonEntity.builder().title("Next.js: Xây dựng ứng dụng SSR và SSG").description("Học cách tối ưu SEO với Next.js.").build(),
                    LessonEntity.builder().title("Xây dựng API với Node.js").description("Dùng Express.js để xây dựng API RESTful.").build(),
                    LessonEntity.builder().title("Tìm hiểu về TypeScript").description("Học cách sử dụng TypeScript để viết code dễ bảo trì hơn.").build(),
                    LessonEntity.builder().title("Dự án thực tế: Xây dựng website bán hàng với React").description("Ứng dụng các kiến thức về React, Redux vào một dự án thực tế.").build(),
                    LessonEntity.builder().title("Tối ưu hiệu suất với React và Next.js").description("Học cách tối ưu và cải thiện hiệu suất cho ứng dụng React.").build()
            );
            List<VideoEntity> videos = videoRepository.findAll();
            List<DocumentEntity> documents = documentRepository.findAll();

            if (videos.size() != lessons.size() || documents.size() != lessons.size()) {
                throw new IllegalStateException("Dữ liệu không khớp: số lượng videos, documents và lessons không bằng nhau.");
            }

            for (int i = 0; i < lessons.size(); i++) {
                LessonEntity lesson = lessons.get(i);

                // 1. Lưu lesson trước
                lesson = lessonRepository.save(lesson);

                // 2. Gán lesson vào video và document
                if (i < videos.size()) {
                    videos.get(i).setLesson(lesson);
                    videoRepository.save(videos.get(i));
                }

                if (i < documents.size()) {
                    documents.get(i).setLesson(lesson);
                    documentRepository.save(documents.get(i));
                }
            }


        }
    }

    private void createQuiz() {
        if (!quizRepository.existsBy()) {
            ExpertEntity expert = expertRepository.findByUser_EmailAndUser_AccountType("vuongtruc2004@gmail.com", AccountTypeEnum.CREDENTIALS)
                    .orElseThrow(() -> new NotFoundException("Expert not found"));
            List<QuestionEntity> questions = questionRepository.findAll();
            List<LessonEntity> lessons = lessonRepository.findAll();
            List<QuizEntity> quizzes = List.of(
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 1")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(0))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 2")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(1))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 3")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(2))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 4")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(3))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 5")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(4))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 6")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(5))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 7")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(6))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 8")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(7))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 9")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(8))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 10")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(9))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 11")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(10))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 12")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(11))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 13")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(12))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 14")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(13))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 15")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(14))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 16")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(15))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 17")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(16))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 18")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(17))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 19")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(18))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 20")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(19))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 21")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(20))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 22")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(21))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 23")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(22))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 24")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(23))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 25")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(24))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 26")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(25))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 27")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(26))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 28")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(27))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 29")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(28))
                            .build(),
                    QuizEntity.builder()
                            .title("Bài kiểm tra số 30")
                            .maxAttempts(random.nextInt(3) + 1)
                            .published(false)
                            .createdAt(Instant.now())
                            .expert(expert)
                            .questions(new HashSet<>(questions))
                            .lesson(lessons.get(29))
                            .build()
            );
            quizRepository.saveAll(quizzes);
        }
    }

    private void createCourse() {
        if (!courseRepository.existsBy()) {
            ExpertEntity expert = expertRepository.findByUser_EmailAndUser_AccountType("vuongtruc2004@gmail.com", AccountTypeEnum.CREDENTIALS)
                    .orElseThrow(() -> new NotFoundException("Expert not found"));
            ExpertEntity expert1 = expertRepository.findByUser_EmailAndUser_AccountType("vuongtruc20042@gmail.com", AccountTypeEnum.CREDENTIALS)
                    .orElseThrow(() -> new NotFoundException("Expert not found"));
            ExpertEntity expert2 = expertRepository.findByUser_EmailAndUser_AccountType("vuongtruc20043@gmail.com", AccountTypeEnum.CREDENTIALS)
                    .orElseThrow(() -> new NotFoundException("Expert not found"));

            List<CourseEntity> courses = new ArrayList<>(List.of(
                    CourseEntity.builder().expert(expert).courseName("Java Cơ Bản").description("Học Java từ cơ bản đến nâng cao.").thumbnail("1.jpg").originalPrice(300000.0).salePrice(255000.0).build(),
                    CourseEntity.builder().expert(expert1).courseName("Lập trình Python").description("Khóa học giúp bạn làm chủ Python.").thumbnail("2.jpg").originalPrice(250000.0).salePrice(225000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Spring Boot Web").description("Xây dựng ứng dụng web với Spring Boot.").thumbnail("3.jpg").originalPrice(400000.0).salePrice(320000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("JavaScript và React").description("Lập trình front-end với React.").thumbnail("4.jpg").originalPrice(350000.0).salePrice(332500.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Lập trình Android").description("Học Kotlin và xây dựng ứng dụng Android.").thumbnail("5.jpg").originalPrice(320000.0).salePrice(262400.0).build(),
                    CourseEntity.builder().expert(expert1).courseName("C++ từ cơ bản đến nâng cao").description("Lập trình C++ cho người mới bắt đầu.").thumbnail("6.jpg").originalPrice(280000.0).salePrice(246400.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Lập trình Node.js").description("Phát triển backend với Node.js và Express.").thumbnail("7.jpg").originalPrice(360000.0).salePrice(270000.0).build(),
                    CourseEntity.builder().expert(expert1).courseName("Fullstack với Next.js").description("Tạo website fullstack với Next.js.").thumbnail("8.jpg").originalPrice(400000.0).salePrice(280000.0).build(),
                    CourseEntity.builder().expert(expert2).courseName("Data Science với Python").description("Phân tích dữ liệu với Python, Pandas.").thumbnail("9.jpg").originalPrice(420000.0).salePrice(378000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Machine Learning").description("Giới thiệu về AI và Machine Learning.").thumbnail("10.jpg").originalPrice(450000.0).salePrice(351000.0).build(),
                    CourseEntity.builder().expert(expert1).courseName("HTML & CSS Cơ Bản").description("Học cách xây dựng giao diện web với HTML và CSS.").thumbnail("11.jpg").originalPrice(180000.0).salePrice(165600.0).build(),
                    CourseEntity.builder().expert(expert2).courseName("SQL cho người mới bắt đầu").description("Học cách truy vấn và quản lý cơ sở dữ liệu với SQL.").thumbnail("12.jpg").originalPrice(150000.0).salePrice(142500.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Kiến trúc Microservices").description("Triển khai hệ thống Microservices với Spring Cloud.").thumbnail("13.jpg").originalPrice(850000.0).salePrice(612000.0).build(),
                    CourseEntity.builder().expert(expert1).courseName("Lập trình Golang").description("Học cách lập trình với ngôn ngữ Golang.").thumbnail("14.jpg").originalPrice(900000.0).salePrice(792000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Kubernetes & Docker").description("Triển khai ứng dụng với Docker và Kubernetes.").thumbnail("15.jpg").originalPrice(950000.0).salePrice(760000.0).build(),
                    CourseEntity.builder().expert(expert2).courseName("Thiết kế UI/UX").description("Học cách thiết kế giao diện đẹp và trải nghiệm tốt.").thumbnail("16.jpg").originalPrice(180000.0).salePrice(180000.0).build(),
                    CourseEntity.builder().expert(expert1).courseName("Cấu trúc dữ liệu & Giải thuật").description("Học thuật toán và cấu trúc dữ liệu để tối ưu code.").thumbnail("17.jpg").originalPrice(190000.0).salePrice(176700.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Lập trình AI với Python").description("Khóa học AI từ cơ bản đến nâng cao với Python.").thumbnail("18.jpg").originalPrice(850000.0).salePrice(595000.0).build(),
                    CourseEntity.builder().expert(expert1).courseName("Blockchain và Smart Contracts").description("Xây dựng ứng dụng phi tập trung với Blockchain.").thumbnail("19.jpg").originalPrice(900000.0).salePrice(675000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("DevOps với AWS và Docker").description("Học DevOps từ cơ bản, triển khai CI/CD với AWS.").thumbnail("20.jpg").originalPrice(1500000.0).salePrice(1050000.0).build()
            ));

            for (CourseEntity course : courses) {
                course.setSubjects(getRandomSubjects());
                course.setAccepted(true);
                course.setObjectiveList(List.of(
                        "Hiểu cơ bản về " + course.getCourseName(),
                        "Nắm vững kỹ thuật lập trình liên quan",
                        "Xây dựng dự án thực tế",
                        "Tự tin ứng dụng vào công việc"
                ));
                course.setUsers(this.getRandomPurchasers());
            }
            courseRepository.saveAll(courses);

            List<CourseEntity> courseEntities = courseRepository.findAll();
            List<LessonEntity> lessonEntities = lessonRepository.findAll();
            for (int i = 0; i < courseEntities.size(); i++) {
                int j = i * 3;
                Set<LessonEntity> lessons = new HashSet<>();
                while (lessons.size() < 3) {
                    lessonEntities.get(j).setCourse(courseEntities.get(i));
                    lessons.add(lessonEntities.get(j));
                    j++;
                }
                courseEntities.get(i).setLessons(lessons);
                courseRepository.saveAndFlush(courseEntities.get(i));
            }
            this.completeSomeDocumentAndVideo();
        }
    }

    private void completeSomeDocumentAndVideo() {
        UserEntity user = userRepository.findByEmailAndAccountType("trucnvhe180248@fpt.edu.vn", AccountTypeEnum.CREDENTIALS)
                .orElseThrow(() -> new UserException("Username not existed!"));
        List<DocumentEntity> documents = documentRepository.findAll();
        List<VideoEntity> videos = videoRepository.findAll();
        user.setCompletedDocuments(new HashSet<>(documents.subList(0, 5)));
        user.setCompletedVideos(new HashSet<>(videos.subList(1, 6)));
        userRepository.save(user);
    }

    private Set<SubjectEntity> getRandomSubjects() {
        Set<SubjectEntity> subjects = new HashSet<>();
        List<SubjectEntity> subjectEntityList = subjectRepository.findAll();
        int length = random.nextInt(subjectEntityList.size());
        for (int i = 0; i < Math.max(length, 1); i++) {
            subjects.add(subjectEntityList.get(random.nextInt(subjectEntityList.size())));
        }
        return subjects;
    }

    private Set<HashtagEntity> getRandomHashtags(Integer numberOfHashtags) {
        List<HashtagEntity> hashtags = hashtagRepository.findAll();
        if (hashtags.size() < numberOfHashtags) {
            throw new IllegalArgumentException("Không đủ hashtag để chọn!");
        }

        Set<HashtagEntity> result = new HashSet<>();

        while (result.size() < numberOfHashtags) {
            result.add(hashtags.get(random.nextInt(hashtags.size())));
        }
        return result;
    }

    private Set<UserEntity> getRandomPurchasers() {
        List<UserEntity> users = userRepository.findAllByRole_RoleName(RoleNameEnum.USER);

        Set<UserEntity> result = new HashSet<>();

        int numberOfPurchasers = random.nextInt(users.size());
        while (result.size() < numberOfPurchasers) {
            result.add(users.get(random.nextInt(users.size())));
        }
        return result;
    }

    private Set<DocumentEntity> getRandomDocuments(Integer numberOfDocuments) {
        List<DocumentEntity> documents = documentRepository.findAll();
        if (documents.size() < numberOfDocuments) {
            throw new IllegalArgumentException("Không đủ document để chọn!");
        }

        Set<DocumentEntity> result = new HashSet<>();

        while (result.size() < numberOfDocuments) {
            result.add(documents.get(random.nextInt(documents.size())));
        }
        return result;
    }

    private Set<VideoEntity> getRandomVideos(Integer numberOfVideos) {
        List<VideoEntity> videos = videoRepository.findAll();
        if (videos.size() < numberOfVideos) {
            throw new IllegalArgumentException("Không đủ video để chọn!");
        }

        Set<VideoEntity> result = new HashSet<>();

        while (result.size() < numberOfVideos) {
            result.add(videos.get(random.nextInt(videos.size())));
        }
        return result;
    }
}