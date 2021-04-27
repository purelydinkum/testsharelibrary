def call(String Git_url,
         String Git_credentials_Id='admin',
         String Param_Type='1',
         String Default_Scan_Bat_Path='default',
         String Additional='default') {
    String Scan_Version = ''
    String Scan_Type = ''
    String Tag_Msg = ''
    String DDSC_Tool_Path = 'D:\\JenkinsTool\\DDSC_Git\\DDSC_Git.exe'
    String Scan_Run_Bat_Path = 'D:\\JenkinsTool\\DDSC_Git\\Fortify_Default.bat'
    String Diff_Report_Path = ''
    String Diff_Files_Path = ''
    List Params = []

    if(Default_Scan_Bat_Path == 'default'){
    
        Params = [
         choice(name: 'ACCOUNT_NAME', choices: ['account1', 'account2'].join('\n'),  description: 'Account Name'),
    ]
    }
    properties([
        parameters([
            booleanParam(defaultValue: false, name: 'BuildAll', description: '')
        ])
    ])

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
                            if (Scan_Bat_Path != 'default'){
                                Scan_Run_Bat_Path = "${WORKSPACE}\\${Scan_Bat_Path}"
                            }
                        }
                }
            }
            stage('Get_Parameter') {
                steps {
                    script {
                            bat """
                                @ECHO OFF
                                echo Parameter List
                                echo Scan_Run_Bat_Path : ${Scan_Run_Bat_Path}
                                echo Scan_Type : ${params.Scan_Type}
                            """
                        }
                }
            }
        }
    }

}
