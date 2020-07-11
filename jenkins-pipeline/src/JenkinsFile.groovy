pipeline{
	agent any
	stages
	{   
		stage('Build')
		{
			steps
			{
				echo "Building the project.."
				sh'''
                    source bin/activate
					pip3 install -r requirements.txt
				    deactivate
                '''
			}
		}
		stage('Collect Static files')
		{
			steps
			{
				echo "Collecting static files.."
				sh'''
					source bin/activate
					python3 manage.py collectstatic --noinput
				    deactivate
				'''
			}
		}
		stage('Run Unit/ Integration Tests')
		{
			steps
			{
				echo "Running Unit/Integration Tests.."
				sh'''
					source bin/activate
					python3 manage.py test
					deactivate
				'''
			}
		}
	}
}