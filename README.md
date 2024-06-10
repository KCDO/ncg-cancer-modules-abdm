# Cancer modules Abdm Profile Generator

## Intention
Provide useful utilities like fhir data generator and validation.

## Before you begin, ensure you have met the following requirements:

Java Development Kit (JDK) version 21
Maven 3.2.5

1. **Clone the Repository**:
    - Clone the repository from the following link:
    ```bash
    git clone https://github.com/KCDO/ncg-cancer-modules-abdm
    ```
    - Navigate into the cloned repository:
    ```bash
    cd ncg-cancer-modules-abdm
    ```

2. **Pull the Latest Changes**:
    - Ensure you are on the main branch and pull the latest updates:
    ```bash
    git checkout main
    git pull origin main
    ```

3. **Build and Run the Project with Maven**:
    - Clean, compile, and package the application using Maven:
    ```bash
    mvnw clean install
    ```

4. **Set project environment variable**
    - all.tests.labs.json=<localpath>/ncg-cancer-modules-abdm/src/main/resources/docs-json/allTestsAndPanels.json
    - op.consultation.input.json=<localpath>/ncg-cancer-modules-abdm/src/main/resources/docs-json/opConsultationInput.json

5. **Run java application**
    cd target 
    ```bash
    java -jar ncg-cancer-modules-abdm-0.0.1-SNAPSHOT.jar
    ```

## Method B: Using Docker Image

1. **Clone the repository from the following link:**
    ```sh
    git clone https://github.com/KCDO/ncg-cancer-modules-abdm
    ```
    Navigate into the cloned repository:
    ```sh
    cd ncg-cancer-modules-abdm
    ```

2. **You can find the Dockerfile at the following directory:**
    ```
    <localpath>\ncg-cancer-modules-abdm
    ```

3. **Host the Dockerfile:**
    ```sh
    docker build -t ncg-cancer-modules-abdm .
    docker run -p 8080:8080 ncg-cancer-modules-abdm
    ```
    This command will start the Docker container, making the KCDO API accessible on port 8080 of your host machine.

4. API:
    - **POST:** `http://localhost:8080/ncg/cancer-modules/abdm/clinical-artifacts`
    - Request body is available under the following directory:
    ```
    <localpath>\ncg-cancer-modules-abdm\project\test\resources
    ```
