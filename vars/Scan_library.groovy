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
        stages {
            stage('Set_Parameter') {
                steps {
                    script {
                            bat """
                                @ECHO OFF
                                echo Set_Parameter
                            """
                        }
                }
            }
            stage('Get_Parameter') {
                steps {
                    script {
                            bat """
                                @ECHO OFF
                                echo Parameter List
                                echo Scan_Bat_Path : ${params.Scan_Bat_Path}
                                echo Scan_Type : ${params.Scan_Type}
                            """
                        }
                }
            }
        }
    }

}
