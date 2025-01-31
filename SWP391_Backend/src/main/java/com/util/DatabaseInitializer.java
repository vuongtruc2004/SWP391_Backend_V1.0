package com.util;

import com.entity.*;
import com.exception.custom.RoleException;
import com.exception.custom.UserException;
import com.repository.*;
import com.util.enums.*;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
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

    @Autowired
    public DatabaseInitializer(RoleRepository roleRepository, PermissionRepository permissionRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, BlogRepository blogRepository, HashtagRepository hashtagRepository, VideoRepository videoRepository, SubjectRepository subjectRepository, DocumentRepository documentRepository, CourseRepository courseRepository, LessonRepository lessonRepository, ExpertRepository expertRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.blogRepository = blogRepository;
        this.hashtagRepository = hashtagRepository;
        this.videoRepository = videoRepository;
        this.subjectRepository = subjectRepository;
        this.documentRepository = documentRepository;
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
        this.expertRepository = expertRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Database initialization started!");

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
        createCourse();

        System.out.println("Database initialization completed!");
    }

    public void createPermission() {
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

    public void createRole() {
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

    public void createUser() {
        if (!userRepository.existsBy()) {
            RoleEntity admin = roleRepository.findByRoleName(RoleNameEnum.ADMIN).orElseThrow(() -> new RoleException("Role name not existed!"));
            RoleEntity expert = roleRepository.findByRoleName(RoleNameEnum.EXPERT).orElseThrow(() -> new RoleException("Role name not existed!"));
            RoleEntity marketing = roleRepository.findByRoleName(RoleNameEnum.MARKETING).orElseThrow(() -> new RoleException("Role name not existed!"));
            RoleEntity user = roleRepository.findByRoleName(RoleNameEnum.USER).orElseThrow(() -> new RoleException("Role name not existed!"));

            List<UserEntity> users = new ArrayList<>(List.of(
                    // manager account
                    UserEntity.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("123"))
                            .avatar("admin.jpg")
                            .fullname("Nguyen Vuong Truc Admin")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("trucnvhe180248@fpt.edu.vn")
                            .gender(GenderEnum.MALE)
                            .job(JobEnum.STUDENT)
                            .role(admin)
                            .build(),
                    UserEntity.builder()
                            .username("expert")
                            .password(passwordEncoder.encode("123"))
                            .avatar("admin.jpg")
                            .fullname("Nguyen Vuong Truc Expert")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("vuongtruc2004@gmail.com")
                            .gender(GenderEnum.MALE)
                            .job(JobEnum.STUDENT)
                            .role(expert)
                            .build(),
                    UserEntity.builder()
                            .username("marketing")
                            .password(passwordEncoder.encode("123"))
                            .avatar("admin.jpg")
                            .fullname("Nguyen Vuong Truc Marketing")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("vuongtruc2008@gmail.com")
                            .gender(GenderEnum.MALE)
                            .job(JobEnum.STUDENT)
                            .role(marketing)
                            .build()
            ));
            for (int i = 1; i <= 10; i++) {
                users.add(
                        UserEntity.builder()
                                .username("user" + i)
                                .password(passwordEncoder.encode("123"))
                                .fullname("Nguyen Vuong Truc " + i)
                                .accountType(AccountTypeEnum.CREDENTIALS)
                                .email(i + "@gmail.com")
                                .gender(i % 2 == 0 ? GenderEnum.MALE : GenderEnum.FEMALE)
                                .job(JobEnum.STUDENT)
                                .role(user)
                                .build()
                );
            }
            userRepository.saveAll(users);
        }
    }

    public void createExpert() {
        if (!expertRepository.existsBy()) {
            UserEntity user = userRepository.findByUsername("expert")
                    .orElseThrow(() -> new UserException("Username not existed!"));
            ExpertEntity expert = ExpertEntity.builder()
                    .diploma(DiplomaEnum.MASTER)
                    .yearOfExperience(10)
                    .user(user)
                    .build();
            expertRepository.save(expert);
        }
    }

    public void createHashtag() {
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

    public void createBlog() {
        if (!blogRepository.existsBy()) {
            UserEntity user = userRepository.findByUsername("marketing").orElseThrow(() -> new UserException("User not existed!"));
            List<BlogEntity> blogs = List.of(
                    BlogEntity.builder()
                            .title("Lập Trình Web Từ A Đến Z: Hướng Dẫn Chi Tiết")
                            .content("<p>Bạn muốn học lập trình web nhưng không biết bắt đầu từ đâu? <strong>Bài viết này</strong> sẽ giúp bạn hiểu rõ các khái niệm quan trọng từ frontend đến backend.</p><p>Bạn sẽ học <em>HTML, CSS, JavaScript, React</em> và <strong>Spring Boot</strong> để xây dựng website chuyên nghiệp.</p>")
                            .plainContent(Jsoup.parse("<p>Bạn muốn học lập trình web nhưng không biết bắt đầu từ đâu? <strong>Bài viết này</strong> sẽ giúp bạn hiểu rõ các khái niệm quan trọng từ frontend đến backend.</p><p>Bạn sẽ học <em>HTML, CSS, JavaScript, React</em> và <strong>Spring Boot</strong> để xây dựng website chuyên nghiệp.</p>").text())
                            .user(user)
                            .thumbnail("1.jpg")
                            .hashtags(this.getRandomHashtags(3))
                            .build(),
                    BlogEntity.builder()
                            .title("Những Sai Lầm Cần Tránh Khi Học Lập Trình")
                            .content("<p>Khi mới học lập trình, nhiều người mắc phải những sai lầm phổ biến. <strong>Bài viết này</strong> sẽ chỉ ra những lỗi đó và cách khắc phục.</p><p>Tránh học lan man, tập trung vào thực hành và tham gia dự án thực tế để tiến bộ nhanh hơn.</p>")
                            .plainContent(Jsoup.parse("<p>Khi mới học lập trình, nhiều người mắc phải những sai lầm phổ biến. <strong>Bài viết này</strong> sẽ chỉ ra những lỗi đó và cách khắc phục.</p><p>Tránh học lan man, tập trung vào thực hành và tham gia dự án thực tế để tiến bộ nhanh hơn.</p>").text())
                            .user(user)
                            .thumbnail("2.jpg")
                            .hashtags(this.getRandomHashtags(5))
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

    public void createVideo() {
        if (!videoRepository.existsBy()) {
            List<VideoEntity> videos = List.of(
                    VideoEntity.builder()
                            .title("When & Where to Add “use client” in React / Next.js (Client Components vs Server Components)")
                            .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                            .videoUrl("https://youtu.be/Qdkg_mrniLk?si=OI-cJQU5nWWqzTls")
                            .build(),
                    VideoEntity.builder()
                            .title("10 common mistakes with the Next.js App Router")
                            .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                            .videoUrl("https://youtu.be/RBM03RihZVs?si=iwBzGi3UH-SnBBw-")
                            .build(),
                    VideoEntity.builder()
                            .title("What is Spring Framework?")
                            .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                            .videoUrl("https://youtu.be/Zxwq3aW9ctU?si=_Q5PIIMS00zQKHWQ")
                            .build(),
                    VideoEntity.builder()
                            .title("Dependency Injection using Spring Boot")
                            .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                            .videoUrl("https://youtu.be/9EoAXpjnsxM?si=YVGJNYXfQDF8_PB9")
                            .build(),
                    VideoEntity.builder()
                            .title("Autowire using Spring Boot")
                            .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                            .videoUrl("https://youtu.be/ET39IFffr24?si=zsQixgt2XigHMWUP")
                            .build(),
                    VideoEntity.builder()
                            .title("TypeScript 5.8 Has 2 AWESOME Features")
                            .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                            .videoUrl("https://youtu.be/vcVoyLQMCxU?si=Ved2PkGSMEEkpJ6I")
                            .build(),
                    VideoEntity.builder()
                            .title("Build anything with DeepSeek + Cline (CHEAP & EASY)")
                            .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                            .videoUrl("https://youtu.be/ksr-_IXsVvs?si=gLpr3GDaAPYzZ6Ly")
                            .build(),
                    VideoEntity.builder()
                            .title("Tailwind v4 Is FINALLY Out – Here’s What’s New (and how to migrate!)")
                            .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                            .videoUrl("https://youtu.be/ud913ekwAOQ?si=25uinR32Zx84cODw")
                            .build(),
                    VideoEntity.builder()
                            .title("My best CSS tips from 2024")
                            .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                            .videoUrl("https://youtu.be/lUU2OAAg4Uw?si=X2HhkieAKwfVFFQy")
                            .build(),
                    VideoEntity.builder()
                            .title("Next.js Server Actions (revalidatePath, useFormStatus & useOptimistic)")
                            .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                            .videoUrl("https://youtu.be/RadgkoJrhu0?si=KBRCSnMIjj_V8-xO")
                            .build()
            );
            videoRepository.saveAll(videos);
        }
    }

    public void createSubject() {
        if (!subjectRepository.existsBy()) {
            List<SubjectEntity> subjects = List.of(
                    SubjectEntity.builder()
                            .subjectName("Java")
                            .description("Ngôn ngữ lập trình phổ biến, chạy trên JVM.")
                            .thumbnail("java.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("Python")
                            .description("Ngôn ngữ lập trình dễ học, mạnh mẽ cho AI, Data Science.")
                            .thumbnail("python.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("JavaScript")
                            .description("Ngôn ngữ lập trình chính cho web frontend.")
                            .thumbnail("javascript.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("C++")
                            .description("Ngôn ngữ lập trình mạnh mẽ cho hệ thống và game.")
                            .thumbnail("cplus.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("C#")
                            .description("Ngôn ngữ phát triển ứng dụng trên nền tảng Microsoft.")
                            .thumbnail("csharp.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("React JS")
                            .description("Thư viện JavaScript phát triển UI động.")
                            .thumbnail("reactjs.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("Next JS")
                            .description("Framework React hỗ trợ SSR và SEO tốt.")
                            .thumbnail("nextjs.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("Spring Boot")
                            .description("Framework Java để phát triển ứng dụng web nhanh chóng.")
                            .thumbnail("springboot.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("PHP")
                            .description("Ngôn ngữ lập trình phổ biến cho backend web.")
                            .thumbnail("php.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("Ruby")
                            .description("Ngôn ngữ lập trình linh hoạt, thường dùng với Rails.")
                            .thumbnail("ruby.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("HTML")
                            .description("Ngôn ngữ đánh dấu để xây dựng trang web.")
                            .thumbnail("html.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("TypeScript")
                            .description("Phiên bản nâng cao của JavaScript với kiểu tĩnh.")
                            .thumbnail("typescript.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("CSS")
                            .description("Ngôn ngữ tạo kiểu cho trang web.")
                            .thumbnail("css.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("SASS")
                            .description("Tiền xử lý CSS giúp viết CSS dễ dàng hơn.")
                            .thumbnail("sass.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("Kotlin")
                            .description("Ngôn ngữ lập trình chính thức cho Android.")
                            .thumbnail("kotlin.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("Swift")
                            .description("Ngôn ngữ lập trình chính thức cho iOS.")
                            .thumbnail("swift.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("Nest JS")
                            .description("Framework Node.js để xây dựng backend hiệu quả.")
                            .thumbnail("nestjs.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("My SQL")
                            .description("Hệ quản trị cơ sở dữ liệu quan hệ phổ biến.")
                            .thumbnail("mysql.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("MongoDB")
                            .description("Cơ sở dữ liệu NoSQL dạng document.")
                            .thumbnail("mongodb.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("Vue JS")
                            .description("Framework JavaScript để xây dựng UI nhanh chóng.")
                            .thumbnail("vuejs.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("Tailwind CSS")
                            .description("Framework CSS tiện lợi, hỗ trợ thiết kế nhanh chóng.")
                            .thumbnail("tailwindcss.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("MUI")
                            .description("Thư viện UI cho React, dựa trên Material Design.")
                            .thumbnail("mui.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("Ant Design")
                            .description("Thư viện UI mạnh mẽ dành cho React.")
                            .thumbnail("antd.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("Bootstrap")
                            .description("Framework CSS phổ biến giúp phát triển web nhanh.")
                            .thumbnail("bootstrap.png")
                            .build(),
                    SubjectEntity.builder()
                            .subjectName("C")
                            .description("Ngôn ngữ lập trình mạnh mẽ, nền tảng cho nhiều ngôn ngữ khác.")
                            .thumbnail("c.png")
                            .build()
            );
            subjectRepository.saveAll(subjects);
        }
    }

    public void createDocument() {
        if (!documentRepository.existsBy()) {
            List<DocumentEntity> documents = List.of(
                    DocumentEntity.builder()
                            .title("Giới thiệu về Java")
                            .content("<h1>Java là gì?</h1><p>Java là một ngôn ngữ lập trình hướng đối tượng, mạnh mẽ.</p>")
                            .plainContent(Jsoup.parse("<h1>Java là gì?</h1><p>Java là một ngôn ngữ lập trình hướng đối tượng, mạnh mẽ.</p>").text())
                            .build(),
                    DocumentEntity.builder()
                            .title("Tìm hiểu về Python")
                            .content("<h1>Python</h1><p>Python là một ngôn ngữ lập trình bậc cao, dễ học.</p>")
                            .plainContent(Jsoup.parse("<h1>Python</h1><p>Python là một ngôn ngữ lập trình bậc cao, dễ học.</p>").text())
                            .build(),
                    DocumentEntity.builder()
                            .title("HTML và CSS là gì?")
                            .content("<h1>HTML & CSS</h1><p>HTML giúp tạo cấu trúc, còn CSS giúp tạo kiểu cho trang web.</p>")
                            .plainContent(Jsoup.parse("<h1>HTML & CSS</h1><p>HTML giúp tạo cấu trúc, còn CSS giúp tạo kiểu cho trang web.</p>").text())
                            .build(),
                    DocumentEntity.builder()
                            .title("Giới thiệu về JavaScript")
                            .content("<h1>JavaScript</h1><p>Ngôn ngữ lập trình phổ biến trên web.</p>")
                            .plainContent(Jsoup.parse("<h1>JavaScript</h1><p>Ngôn ngữ lập trình phổ biến trên web.</p>").text())
                            .build(),
                    DocumentEntity.builder()
                            .title("C++ là gì?")
                            .content("<h1>C++</h1><p>Một ngôn ngữ lập trình mạnh mẽ, dùng cho game, hệ thống nhúng.</p>")
                            .plainContent(Jsoup.parse("<h1>C++</h1><p>Một ngôn ngữ lập trình mạnh mẽ, dùng cho game, hệ thống nhúng.</p>").text())
                            .build(),
                    DocumentEntity.builder()
                            .title("Lập trình với PHP")
                            .content("<h1>PHP</h1><p>PHP là một ngôn ngữ phổ biến để phát triển web.</p>")
                            .plainContent(Jsoup.parse("<h1>PHP</h1><p>PHP là một ngôn ngữ phổ biến để phát triển web.</p>").text())
                            .build(),
                    DocumentEntity.builder()
                            .title("Tìm hiểu về Kotlin")
                            .content("<h1>Kotlin</h1><p>Kotlin là ngôn ngữ chính thức để phát triển Android.</p>")
                            .plainContent(Jsoup.parse("<h1>Kotlin</h1><p>Kotlin là ngôn ngữ chính thức để phát triển Android.</p>").text())
                            .build(),
                    DocumentEntity.builder()
                            .title("Dart và Flutter")
                            .content("<h1>Dart & Flutter</h1><p>Dart là ngôn ngữ, Flutter là framework phát triển ứng dụng mobile.</p>")
                            .plainContent(Jsoup.parse("<h1>Dart & Flutter</h1><p>Dart là ngôn ngữ, Flutter là framework phát triển ứng dụng mobile.</p>").text())
                            .build(),
                    DocumentEntity.builder()
                            .title("Go (Golang) là gì?")
                            .content("<h1>Golang</h1><p>Ngôn ngữ lập trình mạnh mẽ, hiệu suất cao do Google phát triển.</p>")
                            .plainContent(Jsoup.parse("<h1>Golang</h1><p>Ngôn ngữ lập trình mạnh mẽ, hiệu suất cao do Google phát triển.</p>").text())
                            .build(),
                    DocumentEntity.builder()
                            .title("Rust và sự an toàn bộ nhớ")
                            .content("<h1>Rust</h1><p>Rust là một ngôn ngữ lập trình hệ thống với độ an toàn bộ nhớ cao.</p>")
                            .plainContent(Jsoup.parse("<h1>Rust</h1><p>Rust là một ngôn ngữ lập trình hệ thống với độ an toàn bộ nhớ cao.</p>").text())
                            .build()
            );
            documentRepository.saveAll(documents);
        }
    }

    public void createLesson() {
        if (!lessonRepository.existsBy()) {
            List<LessonEntity> lessons = List.of(
                    LessonEntity.builder().title("Giới thiệu về Java").description("Tìm hiểu tổng quan về Java và ứng dụng thực tế.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Lập trình hướng đối tượng").description("Các khái niệm OOP trong Java: class, object, inheritance.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Cấu trúc dữ liệu và giải thuật").description("Các thuật toán sắp xếp, tìm kiếm, danh sách liên kết.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Làm việc với MySQL").description("Cách sử dụng MySQL, thiết kế database.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Spring Boot cơ bản").description("Tìm hiểu về Spring Boot và cách tạo ứng dụng.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("RESTful API với Spring Boot").description("Học cách tạo API trong Spring Boot.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Bảo mật Spring Boot").description("JWT, OAuth2 và bảo mật trong ứng dụng.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Làm việc với Hibernate").description("ORM trong Java với Hibernate.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Lập trình Web với React").description("Giới thiệu về React và cách xây dựng ứng dụng web.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Kết nối Frontend và Backend").description("Cách tích hợp React với API từ Spring Boot.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Redux trong React").description("Quản lý trạng thái ứng dụng với Redux.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Next.js và SSR").description("Tìm hiểu về Next.js và Server-side Rendering.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("CI/CD với GitHub Actions").description("Tự động hóa quy trình triển khai ứng dụng.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Docker và Kubernetes").description("Triển khai ứng dụng với Docker và Kubernetes.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Microservices với Spring Cloud").description("Xây dựng hệ thống Microservices với Spring Cloud.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("GraphQL trong Java").description("Tìm hiểu về GraphQL và cách sử dụng với Spring Boot.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Lập trình Android với Kotlin").description("Xây dựng ứng dụng Android hiện đại với Kotlin.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Machine Learning với Python").description("Cơ bản về Machine Learning, AI, Data Science.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Advanced Java Programming").description("Nâng cao kỹ năng Java với multithreading, stream API.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("TDD với JUnit").description("Lập trình hướng kiểm thử với JUnit và Mockito.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Bộ nhớ đệm với Redis").description("Tăng tốc hiệu suất ứng dụng với Redis Cache.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Kafka và Event-Driven Architecture").description("Xây dựng hệ thống phân tán với Apache Kafka.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Xây dựng API GraphQL").description("Tạo GraphQL API với Spring Boot.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("OAuth2 và OpenID Connect").description("Tích hợp OAuth2 và OpenID Connect trong Spring Security.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("WebSockets với Spring Boot").description("Xây dựng ứng dụng real-time với WebSockets.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Elasticsearch cho Big Data").description("Tìm kiếm nhanh với Elasticsearch và Kibana.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Golang cho Backend Developers").description("Học cách sử dụng Golang để xây dựng API mạnh mẽ.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("TensorFlow với Java").description("Xây dựng mô hình Machine Learning với TensorFlow và Java.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Xây dựng hệ thống CI/CD nâng cao").description("CI/CD chuyên sâu với Kubernetes và ArgoCD.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build(),
                    LessonEntity.builder().title("Xây dựng ứng dụng Serverless").description("Sử dụng AWS Lambda để phát triển ứng dụng không máy chủ.").videos(this.getRandomVideos(3)).documents(this.getRandomDocuments(1)).build()
            );
            lessonRepository.saveAll(lessons);
        }
    }

    public void createCourse() {
        if (!courseRepository.existsBy()) {
            ExpertEntity expert = expertRepository.findByUser_Username("expert")
                    .orElseThrow(() -> new UserException("Username not found!"));
            List<CourseEntity> courses = new ArrayList<>(List.of(
                    CourseEntity.builder().expert(expert).courseName("Java Cơ Bản").description("Học Java từ cơ bản đến nâng cao.").thumbnail("1.jpg").price(300000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Lập trình Python").description("Khóa học giúp bạn làm chủ Python.").thumbnail("2.jpg").price(250000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Spring Boot Web").description("Xây dựng ứng dụng web với Spring Boot.").thumbnail("3.jpg").price(400000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("JavaScript và React").description("Lập trình front-end với React.").thumbnail("4.jpg").price(350000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Lập trình Android").description("Học Kotlin và xây dựng ứng dụng Android.").thumbnail("5.jpg").price(320000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("C++ từ cơ bản đến nâng cao").description("Lập trình C++ cho người mới bắt đầu.").thumbnail("6.jpg").price(280000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Lập trình Node.js").description("Phát triển backend với Node.js và Express.").thumbnail("7.jpg").price(360000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Fullstack với Next.js").description("Tạo website fullstack với Next.js.").thumbnail("8.jpg").price(400000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Data Science với Python").description("Phân tích dữ liệu với Python, Pandas.").thumbnail("9.jpg").price(420000.0).build(),
                    CourseEntity.builder().expert(expert).courseName("Machine Learning").description("Giới thiệu về AI và Machine Learning.").thumbnail("10.jpg").price(450000.0).build()
            ));

            for (CourseEntity course : courses) {
                course.setObjectiveList(List.of(
                        "Hiểu cơ bản về " + course.getCourseName(),
                        "Nắm vững kỹ thuật lập trình liên quan",
                        "Xây dựng dự án thực tế",
                        "Tự tin ứng dụng vào công việc"
                ));
                course.setUsers(this.getRandomPurchasers());
                course.setSubjects(this.getRandomSubjects());
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
        }
    }

    private Set<HashtagEntity> getRandomHashtags(Integer numberOfHashtags) {
        List<HashtagEntity> hashtags = hashtagRepository.findAll();
        if (hashtags.size() < numberOfHashtags) {
            throw new IllegalArgumentException("Không đủ hashtag để chọn!");
        }

        Set<HashtagEntity> result = new HashSet<>();
        Random random = new Random();

        while (result.size() < numberOfHashtags) {
            result.add(hashtags.get(random.nextInt(hashtags.size())));
        }
        return result;
    }

    private Set<UserEntity> getRandomPurchasers() {
        List<UserEntity> users = userRepository.findAllByRole_RoleName(RoleNameEnum.USER);

        Set<UserEntity> result = new HashSet<>();
        Random random = new Random();

        int numberOfPurchasers = random.nextInt(users.size());
        while (result.size() < numberOfPurchasers) {
            result.add(users.get(random.nextInt(users.size())));
        }
        return result;
    }

    private Set<SubjectEntity> getRandomSubjects() {
        List<SubjectEntity> subjects = subjectRepository.findAll();
        Random random = new Random();

        int numberOfSubjects = random.nextInt(subjects.size());

        Set<SubjectEntity> result = new HashSet<>();
        while (result.size() < numberOfSubjects) {
            result.add(subjects.get(random.nextInt(subjects.size())));
        }
        return result;
    }

    private Set<DocumentEntity> getRandomDocuments(Integer numberOfDocuments) {
        List<DocumentEntity> documents = documentRepository.findAll();
        if (documents.size() < numberOfDocuments) {
            throw new IllegalArgumentException("Không đủ document để chọn!");
        }

        Set<DocumentEntity> result = new HashSet<>();
        Random random = new Random();

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
        Random random = new Random();

        while (result.size() < numberOfVideos) {
            result.add(videos.get(random.nextInt(videos.size())));
        }
        return result;
    }
}
