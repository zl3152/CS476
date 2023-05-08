from pyspark.sql import SparkSession
import seaborn as sns
import matplotlib.pyplot as plt

spark = SparkSession.builder.getOrCreate()

merged_data = spark.read.csv("merged_data.csv", header=True, inferSchema=True)

# Select the columns of interest for the heatmap
heatmap_data = merged_data.select('Sleep duration (h) Reference value: 7.0-9.0 hours',
       'Deep sleep (h) Reference value:  2.8-4.4 hours',
       'REM (h) Reference value: 1.4-2.0 hours',
       'Wake (h) Reference value: <= 0.4 hours',
       'Deep sleep onset (min) Reference value: <= 30 minutes',
       'Sleep efficiency Reference value: >= 0.9',
       'Apnea index (/hour) Reference value: < 5/hour', 'ES Score', 'CR Score',
       'SDS', 'PSQI', 'TMD')

heatmap_df = heatmap_data.toPandas()

correlation_matrix = heatmap_df.corr()

# Set the figure size
plt.figure(figsize=(10, 8))

# Generate the heatmap using seaborn
sns.heatmap(correlation_matrix, annot=True, cmap="coolwarm")

# Set the title and labels
plt.title("Correlation Heatmap")
plt.xlabel("Sleep Metrics")
plt.ylabel("Emotional State")

# Display the heatmap
plt.show()
