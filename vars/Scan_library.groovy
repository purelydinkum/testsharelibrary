def call(String Git_url,
         String Git_credentials_Id='admin',
         String Default_Scan_Type='1',
         String Default_Scan_Bat_Path='aaa/bbb.bat',
         String Additional='default') {
    String Scan_Version = ''
    String Scan_Type = ''
    String Tag_Msg = ''
    String Diff_Report_Path = ''
    String Diff_Files_Path = ''

    pipeline {
        agent any
        parameters {
            
            string name: 'Scan_Bat_Path', 
                   defaultValue: Default_Scan_Bat_Path, 
                   description: 'Fortify command path.'
            
            choice name: 'Scan_Type',
                   choices: ['1.Generate diff report, and diff files scan.',
                             '2.Generate diff report, and all files scan.',
                             '3.Only generate diff report.'],
                   description: ''
                   
        
        }
        stages {
            stage('Set_Parameter') {
                steps {
                    script {
                        }
                }
            }
            stage('Get_Parameter') {
                steps {
                    script {
                            bat """
                                @ECHO OFF
                                echo Parameter List
                                echo Scan_Bat_Path : ${Scan_Bat_Path}
                                echo Scan_Type : ${params.Scan_Type}
                            """
                        }
                }
            }
        }
    }

}
