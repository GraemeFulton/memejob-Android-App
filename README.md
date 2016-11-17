# memejob

![](https://scontent.flhr4-1.fna.fbcdn.net/v/t1.0-9/11071514_373798532812666_3422614954300110362_n.jpg?oh=79df227a2ec2c04eca27ddd328f3290a&oe=588DB252)

<p>MemeJob was a project created within a week. It's a bizarre job serach application I made when experimenting with developing native Android applications. I wanted to see how you can use gestures to animate views within an Android application.&nbsp;</p>
<p>Essentially, it's&nbsp;a Job Search app with a bit of a twist,&nbsp;allowing users to search for jobs in a novel way. It works by using information passed in by users to search&nbsp;indeed jobs API, and also fetches an image from my dropbox account to assign to each job post.&nbsp;</p>
<p>
<img src="https://gfulton-images.s3.amazonaws.com/2015/Dec/e6d027a8c54822e8f163b1a74b678bfe-1449333169015.png" alt="" width="287" height="478" />&nbsp; &nbsp;&nbsp;<img src="https://gfulton-images.s3.amazonaws.com/2015/Dec/0bfe23fc799a8bd5fea4011d5ca2b9ed-1449333186312.png" alt="" width="287" height="478" /></p>
<p>This Portfolio Post will start showing the final version of&nbsp;MemeJob, followed by a brief user story to show who MemeJob was for, then the&nbsp;mockup, followed by changes made after user testing. &nbsp; &nbsp;&nbsp;</p>
<p>This was where I left off with this app:</p>
<p><iframe src="https://www.youtube.com/embed/Eu0PnvWsNTw" width="420" height="315" frameborder="0" allowfullscreen="allowfullscreen"></iframe></p>
<h3>Brief user story</h3>
<p>When Thomas is at the pub, he sometimes has to sit on his own and wait for his friends to get served at the bar. During this time, he often pulls out his phone and swipes through pictures on facebook, or twitter. However, Thomas also has his eye out for a new job, and should really be looking for one.</p>
<p><img src="https://gfulton-images.s3.amazonaws.com/2015/Dec/da1e06080254f35c0ed49aafde8a2340-1449333229105.png" alt="User story image" width="440" height="302" /></p>
<h3>User profile</h3>
<ul>
<li>Thomas doesn't like searching for jobs because he finds it boring.</li>
<li>Thomas doesn't enjoy looking through job adverts.</li>
<li>Thomas likes to procrastinate.</li>
<li>Thomas enjoys clicking through funny images, as he likes to be entertained.</li>
<li>Thomas likes to have a laugh.</li>
</ul>
<h3>Wireframing</h3>
<p>The wireframes were created using balsamiq.</p>
<p>Aims:</p>
<ul>
<li>The app must be very easy to use</li>
<li>The app should be fun</li>
<li>There should be minimal need to type anything in in order to browse the jobs</li>
<li>Use of swipe gestures to navigate through jobs</li>
</ul>
<p><img src="https://gfulton-images.s3.amazonaws.com/2015/Dec/490c674f5a5fdba06e59679507478927-1449333252332.jpg" alt="prototype" width="1092" height="617" /></p>
<p><img src="https://gfulton-images.s3.amazonaws.com/2015/Dec/0b3d489e6f4f0304f9e761edac6f50ca-1449333275359.jpg" alt="prototype" width="873" height="546" /></p>
<h3>Changes made after user test</h3>
<p>The original design showed 3 main views of the app:</p>
<ol>
<li>The category select gridview</li>
<li>A second gridview of the category selected</li>
<li>The swipe view (to navigate through grid view items).</li>
</ol>
<p class="p1"><span class="s1">I decided to remove view #2, as it slows down the process of seeing the actual jobs/memes. By removing this layer the user now goes directly from the first category select into swiping through the job list of their category.</span></p>
<p class="p2">It is noticeable that once the user swipes away from a job, they have no direct way of going back to that job. If I had more time for this project, I tried out a bookmarking feature with the use of a RESTful Service. So that when the user swipes right, they can save the job to their profile, but if they swipe left then it is gone.&nbsp;</p>
<h3 class="p2">Overall</h3>
<p>The aim of the project was just to make something and learn technically. It's a fun app, but obviously not very useful as it stands. &nbsp;</p>
