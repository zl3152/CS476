# CS476
1. Describe the data set. 
    • The entire data sets can be categorized into two parts: measurements of sleep quality and questionaire for each participant. 
    • Describe the sleep quality data set
        • This dataset contains the information of 89 healthy ollege students during resting state: the emotion induction and recovery, and a set of cognitive function assessment tasks. 
        • Most informative columns include the measures(sleepquality.csv):
            • fall asleep time
            • wake time
            • Time in bed (h)
            • Sleep duration (h) / Reference value: 7.0-9.0 hours
            • Deep sleep (h) / Reference value:  2.8-4.4 hours
            • Light sleep (h) Reference value: 2.0-2.8 hours
            • REM (h) Reference value: 1.4-2.0 hours
            • Wake (h) Reference value: <= 0.4 hours
            • Deep sleep onset (min) Reference value: <= 30 minutes
            • Sleep efficiency Reference value: >= 0.9
            • Apnea index (/hour) Reference value: < 5/hour
    • Describe the questionaire dataset (scale.xlsx)
        • This dataset can be further divided into 5 data sheets: 
            • Emotion Regulation Questionnaire (ERQ)				
            • Self-Rating Depression Scale (SDS)				
            • Profile of Mood States Questionnaire (POMS) - 40 items				
            • Pittsburgh Sleep Quality Index (PSQI)				
            • Current Emotional Self-Assessment Form (CESAF)	
    • Describe the data sheet ERQ
        • This is the main questionaire that my analysis will focus on 
        • It contains 10 questions, and the response is recorded in 10 columns: 
            '1. When I want to feel more positive emotion (such as joy or amusement), I change what I’m thinking about.',
            '2. I keep my emotions to myself.',
            '3. When I want to feel less negative emotion (such as sadness or anger), I change what I’m thinking about.',
            '4. When I am feeling positive emotions, I am careful not to express them.',
            '5. When I’m faced with a stressful situation, I make myself think about it in a way that helps me stay calm.',
            '6. I control my emotions by not expressing them.',
            '7. When I want to feel more positive emotion, I change the way I’m thinking about the situation.',
            '8. I control my emotions by changing the way I think about the situation I’m in.',
            '9. When I am feeling negative emotions, I make sure not to express them.',
            '10. When I want to feel less negative emotion, I change the way I’m thinking about the situation.'
        • The scale for each of these questions ranges from 1 to 7 (strength of agreement)
        • For simplicity, I will change the column names to A1-A10 in order in replacement of these questions.
        • Noted, A11 and A12 columns are not included as column of interst. 

2. Procedures taken to analyze the data
    1) First, I clean the sleepquality.csv by dropping rows that contain NULL from column 8 to column 10, because these columns contain 
      essential information about one's sleep quality. Any missing values would make the data incomplete to support the analysis. 
    2) With respect to the reference value, which defines a normal range for columns of interest in the study, I add 8 more columns containing 
      binary values, such that an out of range column value would be marked as 1 in its corresponding column, and 0 otherwise. 
    • To simplify the column names, I represent them as A-H, where:
        • A: sleep duration / Reference value: 7.0-9.0 hours
        • B: deep sleep / Reference value:  2.8-4.4 hours
        • C: light sleep / Reference value: 2.0-2.8 hours
        • D: REM / Reference value: 1.4-2.0 hours
        • E: wake / Reference value: <= 0.4 hours
        • F: deep sleep onset Reference value: <= 30 minutes
        • G: sleep efficiency Reference value: >= 0.9
        • H: apnea index Reference value: < 5/hour
    • step 1 and step 2 are done by map and reducer 
    • To explain some the meaning of some colums:
        • apnea index or Apnea Hypopnea Index (AHI): the number of events per hour
            • event: People with OSA experience a collapse of their airways during sleep. When this causes their breathing to completely stop or reduce to 10% of normal levels 
        • REM: The phase of sleep in which most dreams occur. During REM sleep, a person’s brain activity, breathing, heart rate, and blood pressure increase, and the eyes move rapidly while closed.
    3) I clean the ERQ.csv very much the same way I did to sleepquality.csv. Specifically, I drop rows that has a NULL in the field of "complete", since this columns 
       represents a missing of important values if marked "unfinished". (Using MapReduce)
        • After generating the cleaned dataset, I upload it to hdfs and generate the external table henceforth. 
    4) With the table sq (completed from previous homework) and table ERQ (generated just now), I am able to seek for relationships betweem one's sleep quality and emotion status. 
        • I join two tables from the foreign key id, and observe the response values for those marked abnormal in sleep duration. A partition of response value 4 would be fair enough
          to reflect one's emotion status
            • For example, for the question "When I am feeling negative emotions, I make sure not to express them", the majority of students with abnormal sleep duration give a value higher than 4.
              This reveals their inclination to deliberately manage the emotion when it comes to stress. At this point, it may be inferred they would have to deal with stress frequently such that they
              need to learn how to curb it, and such stress may be a factor of abnormal sleep duration. 
            • More of such interpretation will be presented during the class. 
3. Some notes for this homework
    • The screen_shot directory has all necessary screenshots for everything I did for this homework, except for the profiling part. I have include the 
      screenshot for the profiling code in a pdf under profiling_code directory. 
    • Interpretions of relationships between one's sleep quality and their emotion is not fully recorded in this readme, but will instead be presented during the class and the write-ups.
    • Most of the relationships between one's sleep quality and their emotion is drawn from the result from the ETL, so check ETL directory for mora information.
    • Profiling code contains some metrics of the entire data, such as mean and median. I did not include a profiling for the ERQ.csv because it is less meaningful to do similar operations. Instead, 
      the data from ERQ.csv will be more meaningfully employed during the ETL. 
    • The results of the ETL can be viewd from my screen shots under screen_shot directory
    • Cleaning of the code are under ana_code directory, which includes my mapper, reducer, and driver codes. 
    • Essential commands or code to run my program are individually included in corresponding directories. Please check them as needed. 
